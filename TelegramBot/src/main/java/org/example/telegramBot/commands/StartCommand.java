package org.example.telegramBot.commands;

import org.example.telegramBot.service.keyboard.KeyboardService;
import org.example.telegramBot.service.utils.UserNameUtils;
import org.example.telegramBot.service.BotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartCommand extends ServiceCommand {
    @Value("${app.startBot.answer}")
    private String startAnswer;
    private final KeyboardService keyboardService;

    public StartCommand(BotService botService, KeyboardService keyboardService) {
        super("start", "Start Command");
        this.keyboardService = keyboardService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        sendAnswer( absSender,
                    chat.getId(),
                    this.getCommandIdentifier(),
                    UserNameUtils.getUserName(user),
                    startAnswer,
                    keyboardService.getReplyKeyboard("Меню")
                    );
    }
}
