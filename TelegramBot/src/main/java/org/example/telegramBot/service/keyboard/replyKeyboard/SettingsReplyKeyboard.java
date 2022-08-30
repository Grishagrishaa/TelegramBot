package org.example.telegramBot.service.keyboard.replyKeyboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:application-dev.properties", encoding = "UTF-8")
public class SettingsReplyKeyboard extends AbstractReplyKeyboard{
    @Value("${app.settings.message}")
    private String settingsMessage;

    public SettingsReplyKeyboard(@Value("${app.settings.keyboard}")List<String> rows) {
        super("Settings",  rows);
    }

    @Override
    public String getMessage() {
        return settingsMessage;
    }
}
