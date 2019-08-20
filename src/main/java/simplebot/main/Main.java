package simplebot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException, TelegramApiRequestException {
        ApiContextInitializer.init(); // Инициализируем апи
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("simplebot");
        TelegramBotsApi botapi = new TelegramBotsApi();

        try {
            botapi.registerBot((LongPollingBot) context.getBean("bot"));
        } catch (TelegramApiException e) {
            logger.error( e.getCause().getCause().getCause().getMessage());
            Thread.sleep( 5000);

            Main.main(null);
        }
    }
}

