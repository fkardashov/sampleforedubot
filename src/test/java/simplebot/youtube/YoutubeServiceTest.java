package simplebot.youtube;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import simplebot.main.SpringConfig;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class YoutubeServiceTest {
    @Autowired
    private YoutubeService youtubeService;

    @Value("${youtube.videoid}")
    private String videoid;

    @Test
    public void getVideo() {
        File file = youtubeService.getVideo(videoid);
        System.out.println(file.getName());
    }
}