package simplebot.youtube;


import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import simplebot.utils.data.Thread;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;


@Component
public class YoutubeService {
    private final Logger loggger = LoggerFactory.getLogger(YoutubeService.class);
    @Value("${youtube.apiAddress}")
    private String apiAddress;

    public File getVideo(String videoid) {
        final String videoInfoUrl = apiAddress.replace("{videoid}", videoid);
        loggger.debug("Generated URL: " + videoInfoUrl);
        Request request = getRequest( videoInfoUrl);

        try {
            InputStream inputStream = getResponse(request).body().byteStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Map<String, String> mp4Url = parseResponse(bufferedReader.lines().collect(Collectors.joining()));

            if (mp4Url.size() > 0){
                Optional<File> optionalFile = Optional.of(getMP4File(mp4Url.get(String.valueOf(0))));
                return optionalFile.orElse(null);
            }
            else {
                loggger.error("Map for available url is empty!");
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, String> parseResponse(String response) throws UnsupportedEncodingException {
            final String streamKey = "url_encoded_fmt_stream_map";
            final Map<String, String> map = new HashMap<>();
            final Map<String, String> mp4Url = new HashMap<>();

            String[] ampSplit = response.split("&");
            for (String s : ampSplit) {
                final String[] equalsPlit = s.split("=");
                if (equalsPlit.length >= 2)
                    map.put(equalsPlit[0], equalsPlit[1]);
            }

            int count = 0;
            if (map.containsKey(streamKey)) {
                String[] streams = map.get(streamKey).split(",");
                for (String stream : streams) {
                    String[] streamSplit = stream.split("&");
                    for (String s : streamSplit) {
                        final String urlDecoded = URLDecoder.decode(s, "UTF-8");
                        String[] details = urlDecoded.split(",");
                        for (String detail : details) {
                            final String urlContent = URLDecoder.decode(detail, "UTF-8");
                            final String url = urlContent.substring(urlContent.indexOf("https"), urlContent.length());
                            mp4Url.put(Integer.toString(count++), url);
                        }
                    }
                }
            }
            mp4Url.values().stream().forEach(string -> loggger.debug(string));
            return mp4Url;
    }


    private File getMP4File(String url) throws IOException{
    Request request = getRequest(url);

        File newFile = null;
        try {

            byte[] bytes = getResponse(request).body().bytes();

            newFile = File.createTempFile("video", ".mp4");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(newFile));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        }
        catch (IOException e){
                e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return newFile;
    }
    private void printDivider() {
        System.out.println("------------------------------");
    }

    private Request getRequest( String url){
        return new Request.Builder()
                .cacheControl(CacheControl.FORCE_NETWORK)
                .url(url)
                .build();
    }

    private Response getResponse(Request request) throws IOException, InterruptedException {
        int retryCount = 0;
        int maxRetryCount = 5;
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();

        while (!response.isSuccessful() && retryCount++ < maxRetryCount){
            loggger.debug("Unexpected response retry.");
            loggger.debug("Response code: " + response.code());
            loggger.debug(response.toString());
            sleep(2000);
            response = okHttpClient.newCall(request).execute();
        }
        return response;
    }
}