package org.example.telegramBot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.HashMap;

@Component
public class CurrencyModeService {
    private final HashMap<Long, String> originalMemory = new HashMap<>();
    private final HashMap<Long, String> targetMemory = new HashMap<>();

    public String getOriginalMemory(Long chatId){
        return originalMemory.getOrDefault(chatId, "BYN");
    }

    public String getTargetMemory(Long chatId){
        return targetMemory.getOrDefault(chatId, "USD");
    }

    public void updateMode(CallbackQuery callbackQuery){
        Long chatId = callbackQuery.getMessage().getChatId();
        String currency = callbackQuery.getData().split("\\|")[2];

        String action = callbackQuery.getData().split("\\|")[1];
        switch (action){
            case "ORIGINAL":
                originalMemory.put(chatId, currency);
                return;
            case "TARGET":
                targetMemory.put(chatId, currency);
        }
    }
}
