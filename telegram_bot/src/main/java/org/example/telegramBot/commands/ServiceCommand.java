package org.example.telegramBot.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public abstract class ServiceCommand extends BotCommand {
    private final Logger logger = LoggerFactory.getLogger(ServiceCommand.class);

    public ServiceCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }


    public void sendAnswer(AbsSender absSender, Long chatId, String commandName, String userName, String text, ReplyKeyboard keyboard) {
        SendMessage message = SendMessage.builder()
                                         .parseMode("HTML")
                                         .chatId(chatId.toString())
                                         .text(text)
                                         .replyMarkup(keyboard)
                                         .build();

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            logger.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
            e.printStackTrace();
        }
    }
}
