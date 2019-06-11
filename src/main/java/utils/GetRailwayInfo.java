package utils;

import com.google.gson.Gson;
import utils.config.Config;
import utils.data.RailWayInfo;
import utils.data.Segment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MINUTES;

public class GetRailwayInfo {
    private final String apiGetTemplate = "https://api.rasp.yandex.net/v3.0/search/?apikey={apikey}&format=json&from={from}&to={to}&lang=ru_RU&page=1&transport_types=suburban&limit=100";

    private final Logger logger = LoggerFactory.getLogger(GetExchangeData.class);
    private static final Gson gson = new Gson();
    private RailWayInfo railWayInfo;

    public GetRailwayInfo( String from, String to){

        try {
            URL url = new URL(getUrlString( from, to));
            logger.info("Старт парсинга json от яндекс расписаний");
            railWayInfo = gson.fromJson( getJsonfromYandex( url), RailWayInfo.class);
            logger.info("Конец парсинга json от яндекс расписаний");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrlString(String from, String to){

        return apiGetTemplate
                .replace("{to}", to)
                .replace("{from}", from)
                .replace("{apikey}", Config.getInstance().getProperties().getProperty("yandex.pasp"));
    }

    public String getJsonfromYandex( URL url) throws IOException {
        logger.info("Старт запроса в яндекс апи");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setReadTimeout(10000);

        BufferedReader in = new BufferedReader(new InputStreamReader( con.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        in.close();

        logger.info(stringBuilder.toString());
        logger.info("Конец запроса в яндекс апи");
        return stringBuilder.toString();
    }

    public String getData(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        railWayInfo.segments.stream()
                .filter( x -> LocalTime.now().isBefore(LocalTime.parse(x.departure)))
                .filter( x -> LocalDate.now().equals( LocalDate.parse(x.start_date)))
                .limit(5)
                .forEach(x -> {if( x !=null ) stringBuilder.append(getDataFromThread( x ));});

        logger.info(stringBuilder.toString());
        return (stringBuilder.length() > 0) ? stringBuilder.toString() : "Ничего не нашлось :-(";
    }

    public String getDataFromThread( Segment segment){
        return segment.departure+ " "
                + segment.thread.title + " "
                + segment.stops + "     **" +
                Math.abs(LocalTime.parse(segment.departure).until( LocalTime.now(), MINUTES)) +
                 "**\n";
    }
}
