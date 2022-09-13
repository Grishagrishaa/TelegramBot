package org.example.telegramBot.service.keyboard;

import org.example.telegramBot.service.CurrencyModeService;
import org.example.telegramBot.service.financeService.FinanceService;
import org.example.telegramBot.service.keyboard.inlineKeyboard.AbstractInlineKeyboard;
import org.example.telegramBot.service.keyboard.replyKeyboard.AbstractReplyKeyboard;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.*;
import static org.example.telegramBot.service.enums.ECallbackAction.*;

@Service
public class KeyboardService {
    private final FinanceService financeService;
    private final CurrencyModeService modeService;
    private Map<String, AbstractReplyKeyboard> replyContainer;
    private Map<String, AbstractInlineKeyboard> inlineContainer;

    public KeyboardService(List<AbstractReplyKeyboard> replyContainer, List<AbstractInlineKeyboard> inlineContainer, FinanceService financeService, CurrencyModeService modeService) {
        this.financeService = financeService;
        this.modeService = modeService;
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

    public InlineKeyboardMarkup getPaginationKeyboard(Integer totalPages, String currency, Integer chosenPage){

        List<InlineKeyboardButton> buttons = new ArrayList<>();

         for (int i = 1; i < totalPages + 1; i++) {
             buttons.add(InlineKeyboardButton.builder()
                     .text(getChosenButton(i, chosenPage))
                     .callbackData(String.join("|", PAGINATION.toString(), Integer.toString(i), currency))
                     .build());//PAGINATION|1|USD = CALLBACK_ACTION|PAGE_NUMBER|CURRENCY_TI_MAKE REQUEST
        }
        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(buttons))
                .build();
    }

    public InlineKeyboardMarkup updateKeyboard(Long chatId){
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (String currency : financeService.getConvertCurrencies()) {
            buttons.add(
                    Arrays.asList(
                            InlineKeyboardButton.builder()
                                    .text(getChosenButton(currency, modeService.getOriginalMemory(chatId)))
                                    .callbackData(CONVERT + "|ORIGINAL|" + currency)
                                    .build(),
                            InlineKeyboardButton.builder()
                                    .text(getChosenButton(currency, modeService.getTargetMemory(chatId)))
                                    .callbackData(CONVERT + "|TARGET|" + currency)
                                    .build()
                    ));
        }
        return InlineKeyboardMarkup.builder()
                .keyboard(buttons)
                .build();
    }

    public String getChosenButton(Integer current, Integer saved) {
        return Objects.equals(saved, current) ? current + " ✅" : current.toString();
    }

    public String getChosenButton(String current, String saved) {
        return Objects.equals(saved, current) ? current + " ✅" : current;
    }
}

