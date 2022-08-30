package org.example.telegramBot.service.keyboard;

import org.example.telegramBot.service.keyboard.inlineKeyboard.AbstractInlineKeyboard;
import org.example.telegramBot.service.keyboard.replyKeyboard.AbstractReplyKeyboard;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeyboardService {
    private Map<String, AbstractReplyKeyboard> replyContainer;
    private Map<String, AbstractInlineKeyboard> inlineContainer;

    public KeyboardService(List<AbstractReplyKeyboard> replyContainer, List<AbstractInlineKeyboard> inlineContainer) {
        this.replyContainer = new HashMap<>();
        this.inlineContainer = new HashMap<>();
        replyContainer.forEach(k -> this.replyContainer.put(k.getKeyboardName(), k));
        inlineContainer.forEach(k -> this.inlineContainer.put(k.getCallBackAction().getMessage(), k));
    }

    public ReplyKeyboard getReplyKeyboard(String keyboardName){
        if(inlineContainer.containsKey(keyboardName)){
            return inlineContainer.get(keyboardName);
        }else if(replyContainer.containsKey(keyboardName)){
            return replyContainer.get(keyboardName);
        }
        return replyContainer.get("Menu");
    }

    public String getMessage(String keyboardName){
        if(inlineContainer.containsKey(keyboardName)){
            return inlineContainer.get(keyboardName).getMessage();
        }else if(replyContainer.containsKey(keyboardName)){
            return replyContainer.get(keyboardName).getMessage();
        }
        return replyContainer.get("Menu").getMessage();
    }
}

