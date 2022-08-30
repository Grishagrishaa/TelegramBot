package org.example.telegramBot;

import org.example.telegramBot.commands.StartCommand;
import org.example.telegramBot.service.BotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class MyBot extends TelegramLongPollingCommandBot {
    @Value("${app.botName}")
    private String BOT_NAME;
    @Value("${app.botToken}")
    private String BOT_TOKEN;
    private final ApplicationContext context;
    private final Logger logger = LoggerFactory.getLogger(MyBot.class);
    private final BotService service;


    public MyBot(ApplicationContext context, BotService service){
        super();
        this.context = context;
        this.service = service;
        register(context.getBean(StartCommand.class));
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        BotApiMethod sendMsg = null;
        if(update.hasMessage()){
            sendMsg = service.getAnswer(update.getMessage());
        }else if(update.hasCallbackQuery()){
            sendMsg = service.getAnswer(update.getCallbackQuery());
        }else {
            //todo default
        }

        try {
            execute(sendMsg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
