package org.example.telegramBot.service;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface IBotService {

    /**
     *
     * @param callback
     * @return
     */
    BotApiMethod handleCallback(CallbackQuery callback);
}
