package simplebot.utils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import simplebot.utils.data.RailWayInfo;
import simplebot.utils.data.Segment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringJoiner;

import static java.time.temporal.ChronoUnit.MINUTES;

@Component
@PropertySource("classpath:config.properties")
public class GetRailwayInfo {
    private final String apiTemplate = "https://api.rasp.yandex.net/v3.0/search/?apikey={apikey}&format=json&from={from}&to={to}&lang=ru_RU&page=1&transport_types=suburban&limit=100";
    private static final Gson gson = new Gson();
    private RailWayInfo railWayInfo;

    @Value("${yandex.pasp}")
    private String apikey;


    public GetRailwayInfo init(String from, String to){
        try {
            URL url = new URL(getUrlString( from, to));
            railWayInfo = gson.fromJson( getJsonfromYandex( url), RailWayInfo.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getUrlString(String from, String to){

        return apiTemplate
                .replace("{to}", to)
                .replace("{from}", from)
                .replace("{apikey}", apikey);
    }

    public String getJsonfromYandex( URL url) {
        StringBuilder stringBuilder = new StringBuilder();

        HttpURLConnection urlConnection;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public String getData(){
        StringJoiner stringJoiner = new StringJoiner("\n");

        railWayInfo.segments.stream()
                .filter( train -> LocalTime.now().isBefore( LocalTime.parse( train.departure)))
                .filter( train -> LocalDate.now().equals( LocalDate.parse( train.start_date)))
                .limit(5)
                .forEach( train -> {
                    if( train != null )
                        stringJoiner.add(getDataFromThread( train));
                });

        return (stringJoiner.length() > 0) ? stringJoiner.toString() : "Ничего не нашлось :-(";
    }

    public String getDataFromThread( Segment segment){
        return segment.departure+ " "
                + segment.thread.title + " "
                + segment.stops + "     " +
                Math.abs(LocalTime.parse(segment.departure).until( LocalTime.now(), MINUTES));
    }
}
