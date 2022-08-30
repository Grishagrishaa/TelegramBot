package org.example.telegramBot.service.utils;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;


public class UserNameUtils {
    public static String getUserName(Message message){
        return getUserName(message.getFrom());
    }

    public static String getUserName(User user){
        return user.getUserName() != null ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getUserName());
    }
}
