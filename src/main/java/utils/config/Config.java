package utils.config;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config ourInstance = new Config();
    public static Config getInstance() {
        return ourInstance;
    }

    private Properties properties;

    public Properties getProperties() {
        return properties;
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
