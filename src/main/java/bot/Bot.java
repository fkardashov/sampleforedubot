package bot;

import utils.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import utils.GetExchangeData;
import utils.GetRailwayInfo;

public class Bot extends TelegramLongPollingBot {
    private final Logger loggger = LoggerFactory.getLogger(Bot.class);
    private final Config config = Config.getInstance();
    private final GetExchangeData getExchangeData = GetExchangeData.getInstance();

    public Bot() {
        super();
    }

    @Override
    public void onUpdateReceived(Update update) {
        loggger.debug("Get message" + update.toString());

        Message msg = update.getMessage();
        String txt = msg.getText();

        if (txt.equals("/start")) {
            sendMsg( update.getMessage().getChatId().toString(), "/exchange  /work /home");
        }

        if( txt.equals("/exchange")){
            sendMsg(update.getMessage().getChatId().toString(), getExchangeData.getData() );
        }

        if( txt.equals("/work")){
            sendMsg(update.getMessage().getChatId().toString(), new GetRailwayInfo( "s9601364", "s9601688").getData());
        }

        if( txt.equals("/home")){
            sendMsg(update.getMessage().getChatId().toString(), new GetRailwayInfo( "s9601688", "s9601364").getData());
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
