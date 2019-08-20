package simplebot.youtube;

import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoFileInfo;
import com.github.axet.vget.info.VideoInfo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class YoutubeService {
    public File getVideo(String address){
        try {
            File file;
            address = "https://www.youtube.com/watch?v=" + address;
            System.out.println( address);
            file = File.createTempFile("prefix1" + ThreadLocalRandom.current().nextLong(), ".suffix");
            VGet v = new VGet(new URL(address));
            System.out.println(file.getName());
            v.download();

            return file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
