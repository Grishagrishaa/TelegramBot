package org.example.telegramBot.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.telegramBot.service.enums.ECallbackAction;
import org.example.telegramBot.service.impl.UserData;
import org.example.telegramBot.service.impl.currency.api.ICurrencyDto;
import org.example.telegramBot.service.keyboard.KeyboardService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class BotService {
    private final CurrencyModeService modeService;
    private final UserService userService;
    private final FinanceService financeService;
    private final KeyboardService keyboardService;

    private final StringBuffer stringBuffer;
    private final NumberFormat format;


    public BotService(CurrencyModeService modeService, UserService userService, FinanceService financeService, KeyboardService keyboardService) {
        this.modeService = modeService;
        this.userService = userService;
        this.financeService = financeService;
        this.keyboardService = keyboardService;
        this.format = NumberFormat.getInstance();
        stringBuffer = new StringBuffer();
    }

    public List<List<InlineKeyboardButton>> updateKeyboard(Long chatId){
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        for (String currency : financeService.getConvertCurrencies()) {
            buttons.add(
                    Arrays.asList(
                            InlineKeyboardButton.builder()
                                    .text(getChosenCurrency(currency, modeService.getOriginalMemory(chatId)))
                                    .callbackData("CONVERT|ORIGINAL|" + currency)
                                    .build(),
                            InlineKeyboardButton.builder()
                                    .text(getChosenCurrency(currency, modeService.getTargetMemory(chatId)))
                                    .callbackData("CONVERT|TARGET|" + currency)
                                    .build()
                    ));
        }
        return buttons;
    }

    public String getChosenCurrency(String current, String saved) {
        return Objects.equals(saved, current) ? current + " ✅" : current;
    }

    public BotApiMethod getAnswer(CallbackQuery callback){
        BotApiMethod message = null;
        Long chatId = callback.getMessage().getChatId();

        UserData userData = UserData.getDefaults();
        try{
            userData = userService.getUserDataByUserId(callback.getFrom().getId());
        }catch (WebClientException e){
            userService.createUser(new UserData(callback.getFrom().getId()));
        }

        ECallbackAction action = ECallbackAction.valueOf(callback.getData().split("\\|")[0]);
        switch (action){
            case CONVERT:
                //just handle clicks to update chosen currency
                modeService.updateMode(callback);
                return message = EditMessageReplyMarkup.builder()
                        .messageId(callback.getMessage().getMessageId())
                        .chatId(chatId)
                        .replyMarkup(
                                InlineKeyboardMarkup.builder()
                                        .keyboard(updateKeyboard(chatId))
                                        .build())
                        .build();
            case RATES:
                String currency = callback.getData().split("\\|")[1];
                if(userData.getCity() == null){
                    return SendMessage.builder()
                            .parseMode("HTML")
                            .chatId(chatId.toString())
                            .text("You can acces only NBRB rates until you choose your region in <b>Settings</b>" + '\n'
                                    + formMessageOfEntities(financeService.getBestNbrbRates()))
                            .build();

                }else {
                    return SendMessage.builder()
                            .parseMode("HTML")
                            .chatId(chatId.toString())
                            .text(formMessageOfEntities(financeService.getBestRates(userData.getCity(), currency)))
                            .build();
                }
        }

        return message;
    }

    public BotApiMethod getAnswer(Message message){
        final Long chatId = message.getChatId();
        final String text = message.getText();

        if (NumberUtils.isParsable(text.replaceAll("\\s+", ""))) {
            return SendMessage.builder()
                    .parseMode("HTML")
                    .chatId(chatId.toString())
                    .replyMarkup(keyboardService.getReplyKeyboard(text))
                    .text(convert(chatId, text))
                    .build();
        }
        return SendMessage.builder()
                .parseMode("HTML")
                .chatId(chatId.toString())
                .replyMarkup(keyboardService.getReplyKeyboard(text))
                .text(keyboardService.getMessage(text))
                .build();

    }

    public String convert(Long chatId, String text){
        String originalMemory = modeService.getOriginalMemory(chatId);
        String targetMemory = modeService.getTargetMemory(chatId);

        double original = Double.parseDouble(text.replaceAll("\\s+", ""));
        Double conversionRatio = financeService.getRate(originalMemory, targetMemory);

        String answer = stringBuffer
                .append(format.format(original)).append(" ").append(originalMemory)
                .append(" = ")
                .append(format.format(original * conversionRatio))
                .append(" ").append(targetMemory).toString();
        stringBuffer.setLength(0);
        return answer;
    }

    private String formMessageOfEntities(ICurrencyDto[] currencyDtos){
        Arrays.stream(currencyDtos).forEach(c -> stringBuffer.append(c.toString()).append('\n'));
        String message = stringBuffer.toString();
        stringBuffer.setLength(0);
        return message;
    }
}

