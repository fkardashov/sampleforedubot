package simplebot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import simplebot.utils.GetExchangeData;
import simplebot.utils.GetRailwayInfo;
import simplebot.utils.config.Config;
import simplebot.utils.task.TaskService;
import simplebot.youtube.YoutubeService;

import java.io.File;

@Component
public class Bot extends TelegramLongPollingBot {
    private final Logger loggger = LoggerFactory.getLogger(Bot.class);
    @Autowired
    private GetExchangeData getExchangeData;
    @Autowired
    private GetRailwayInfo getRailwayInfo;
    @Autowired
    private TaskService taskService;
    @Autowired
    YoutubeService youtubeService;

    @Override
    public void onUpdateReceived(Update update) {
        loggger.debug("Get message" + update.toString());

        Message msg = update.getMessage();
        String txt = msg.getText();

        if (txt.startsWith("/youtube")){
            File file = youtubeService.getVideo( txt.replaceAll("/youtube", "").trim());
            SendVideo sendVideo = new SendVideo();
            sendVideo.setChatId(update.getMessage().getChatId().toString());
            sendVideo.setVideo(file);
            sendVideo.setCaption("caption");
            try {
                execute(sendVideo);
            } catch (TelegramApiException e) {
                loggger.error("Exception: ", e.toString());
            }
        }

        if (txt.equals("/start")) {
            sendMsg( update.getMessage().getChatId().toString(), "/exchange /work /home");
        }

        if( txt.equals("/exchange")){
            sendMsg(update.getMessage().getChatId().toString(), getExchangeData.getData() );
        }

        if( txt.equals("/work")){
            sendMsg(update.getMessage().getChatId().toString(), getRailwayInfo.init( "s9601364", "s9601688").getData());
        }

        if( txt.equals("/home")){
            sendMsg(update.getMessage().getChatId().toString(), getRailwayInfo.init( "s9601688", "s9601364").getData());
        }

        if (txt.startsWith("/addtask")) {
            taskService.addTask( txt.replaceAll( "/addtask ", ""));
            sendMsg(update.getMessage().getChatId().toString(), "task add.");
        }
        if (txt.startsWith("/deltask")) {
            taskService.deleteTask( txt.replaceAll( "/deltask ", ""));
            sendMsg(update.getMessage().getChatId().toString(), taskService.toString());
        }

        if (txt.startsWith("/tasklist")) {
            sendMsg(update.getMessage().getChatId().toString(), taskService.toString());
        }
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            loggger.error("Exception: ", e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return Config.getInstance().getProperties().getProperty("bot.name");
    }

    @Override
    public String getBotToken() {
        return Config.getInstance().getProperties().getProperty("bot.token");
    }
}
