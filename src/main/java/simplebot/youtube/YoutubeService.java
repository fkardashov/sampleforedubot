package simplebot.youtube;

import com.github.axet.vget.VGet;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class YoutubeService {
    public File getVideo(String address){
        try {
            File file;
            address = "https://www.youtube.com/watch?v=" + address;
            System.out.println( address);
            file = File.createTempFile("prefix1" + ThreadLocalRandom.current().nextLong(), ".suffix");
            VGet v = new VGet(new URL(address), file);
            System.out.println(file.getName());
            v.getVideo();

            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
