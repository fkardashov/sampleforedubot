package simplebot.utils.config;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config ourInstance = null;
    private Properties properties;

    public static Config getInstance() {
        if ( ourInstance == null)
            ourInstance = new Config();

        return ourInstance;
    }

    public Properties getProperties() {
        if ( ourInstance == null)
            ourInstance = new Config();

        return ourInstance.properties;
    }

    private Config() {
        properties = new Properties();

        try {
            properties.load( getClass().getResourceAsStream("/config.properties"));

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            throw new Error();
        }
    }
}
