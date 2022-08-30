package org.example.telegramBot.converter;

import org.example.telegramBot.service.impl.UserData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class TelegramUserToMyUserConverter implements Converter<User, UserData> {
    @Override
    public UserData convert(User user) {
        return new UserData(user.getId());
    }
}
