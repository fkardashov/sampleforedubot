package utils;

import com.google.inject.Singleton;
import utils.data.ValCurs;
import utils.data.Valute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
@Singleton
public class GetExchangeData {
    private final Logger logger = LoggerFactory.getLogger( GetExchangeData.class);

    public static GetExchangeData getInstance(){ return getExchangeData;}
    private static GetExchangeData getExchangeData = new GetExchangeData();

    private final String cbURL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private ValCurs valCurs;
    private long lastUpdate;

    private GetExchangeData() { InitialData(); }

    public void InitialData(){
        lastUpdate = System.currentTimeMillis();
        JAXBContext jc = null;

        try {
            logger.info("Create Marshaller" + cbURL);

            URL url = new URL(cbURL);

            jc = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            valCurs = (ValCurs) unmarshaller.unmarshal( url);

            logger.info(valCurs.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(){
        if( System.currentTimeMillis() - lastUpdate > 120 * 1000)
            InitialData();

        StringBuilder stringBuilder = new StringBuilder();

        for( Valute valute : valCurs.getValute())
            if(valute.getCharCode().equals("USD") || valute.getCharCode().equals("EUR"))
                stringBuilder.append( valute.getName() + " : " + valute.getValue() + "\n");

        return stringBuilder.toString();
    }
}
