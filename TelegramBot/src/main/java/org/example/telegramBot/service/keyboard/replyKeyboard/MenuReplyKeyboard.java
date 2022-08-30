package org.example.telegramBot.service.keyboard.replyKeyboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:application-dev.properties", encoding = "UTF-8")
public class MenuReplyKeyboard extends AbstractReplyKeyboard{
    @Value("${app.menu.message}")
    private String menuMessage;

    public MenuReplyKeyboard(@Value("${app.menu.keyboard}")List<String> buttons) {
        super("Menu",  buttons);
    }

    @Override
    public String getMessage() {
        return menuMessage;
    }
}
