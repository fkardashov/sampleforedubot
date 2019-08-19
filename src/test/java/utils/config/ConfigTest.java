package utils.config;

import org.junit.Test;
import simplebot.utils.config.Config;

import static org.junit.Assert.*;

public class ConfigTest {
    Config config = Config.getInstance();

    @Test
    public void getInstance() {
        assertNotNull( config.getInstance());
    }

    @Test
    public void getProperties() {
        assertNotNull( Config.getInstance().getProperties());
    }
}