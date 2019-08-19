package simplebot.utils;

import org.springframework.stereotype.Component;
import simplebot.utils.data.ValCurs;
import simplebot.utils.data.Valute;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class GetExchangeData {
    private final String cbURL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private ValCurs valCurs;
    private long lastUpdate;

    public void InitialData(){
        lastUpdate = System.currentTimeMillis();
        JAXBContext jc = null;

        try {
            URL url = new URL(cbURL);

            jc = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            valCurs = (ValCurs) unmarshaller.unmarshal( url);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(){
        if( lastUpdate == 0 || System.currentTimeMillis() - lastUpdate > 120 * 1000)
            InitialData();

        StringBuilder stringBuilder = new StringBuilder();

        for( Valute valute : valCurs.getValute())
            if( valute.getCharCode() != null && (valute.getCharCode().equals("USD") || valute.getCharCode().equals("EUR")))
                stringBuilder.append( valute.getName() + " : " + valute.getValue() + "\n");

        return stringBuilder.toString();
    }
}
