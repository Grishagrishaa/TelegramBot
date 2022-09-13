package org.example.telegramBot.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.telegramBot.service.enums.ECallbackAction;
import org.example.telegramBot.service.financeService.FinanceService;
import org.example.telegramBot.service.impl.userService.UserData;
import org.example.telegramBot.service.impl.financeService.dto.CurrencyMyfinDto;
import org.example.telegramBot.service.impl.financeService.dto.CurrencyMyfinNbrbDto;
import org.example.telegramBot.service.impl.financeService.pagination.Page;
import org.example.telegramBot.service.impl.financeService.pagination.PageRequest;
import org.example.telegramBot.service.keyboard.KeyboardService;
import org.example.telegramBot.service.userService.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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

    public BotApiMethod handleCallback(CallbackQuery callback){
        Message message = callback.getMessage();
        Long chatId = message.getChatId();
        Long userId = callback.getFrom().getId();

        UserData userData = UserData.getDefaults(userId);
        try{
            userData = userService.getUserDataByUserId(userId);
        }catch (WebClientException e){
            userService.createUser(userData);
        }

        String city = userData.getCity();
        Integer pageSize = userData.getPageSize();

        ECallbackAction callbackAction = ECallbackAction.valueOf(callback.getData().split("\\|")[0]);
        switch (callbackAction){
            case CONVERT:
                //just handle clicks to update chosen currency
                modeService.updateMode(callback);
                return editMessage(message,
                                   message.getText(),
                                   keyboardService.updateKeyboard(chatId));
            case RATES:
                String currency = callback.getData().split("\\|")[1];//todo "" in url
                if(city == null || Objects.equals(city, "NBRB")){
                    Page<CurrencyMyfinNbrbDto> page = financeService.getBestNbrbRates(PageRequest.of(1, pageSize));
                    return sendMessage(
                            chatId,
                            "National Bank Rates. Cause no city chosen in Settings. " + formMessageOfEntities(page.getContent(), city),
                            keyboardService.getPaginationKeyboard(page.getTotalPages(), currency, 1));
                }else {
                    Page<CurrencyMyfinDto> page = financeService.getBestRates(city, currency, PageRequest.of(1, pageSize));
                    return sendMessage(chatId,
                                       formMessageOfEntities(page.getContent(), city),
                                       keyboardService.getPaginationKeyboard(page.getTotalPages(), currency,1));

                }
            case PAGINATION:
                Integer pageNumber = Integer.valueOf(callback.getData().split("\\|")[1]);
                String cur = callback.getData().split("\\|")[2];//todo "" in url

                if(city == null || Objects.equals(city, "NBRB")) {
                    Page<CurrencyMyfinNbrbDto> page = financeService.getBestNbrbRates(PageRequest.of(pageNumber, pageSize));
                    return editMessage(message,
                                       "National Bank Rates. Cause no city chosen in <b>Settings</b>. " + formMessageOfEntities(page.getContent(), city),
                                       keyboardService.getPaginationKeyboard(page.getTotalPages(), cur, pageNumber));
                }else {
                    Page<CurrencyMyfinDto> page = financeService.getBestRates(city, cur, PageRequest.of(pageNumber, pageSize));
                    return editMessage(message,
                                       formMessageOfEntities(page.getContent(), city),
                                       keyboardService.getPaginationKeyboard(page.getTotalPages(), cur, pageNumber));
                }

            case CITY:
                String cityButton = callback.getData().split("\\|")[1];
                userData.setCity(cityButton);
                try {
                    userService.updateUser(userData);
                    return sendMessage(chatId, "City was updated", keyboardService.getReplyKeyboard("Menu"));
                } catch (WebClientException e) {
                    sendMessage(chatId,
                            "City wasn't updated",
                            keyboardService.getReplyKeyboard("Menu"));
                }

            case PAGE_SIZE:
                Integer pageSizeButton = Integer.valueOf(callback.getData().split("\\|")[1]);
                userData.setPageSize(pageSizeButton);
                try {
                    userService.updateUser(userData);
                    return sendMessage(chatId, "Page size was updated", keyboardService.getReplyKeyboard("Menu"));
                } catch (WebClientException e) {
                    sendMessage(chatId,
                            "Page size wasn't updated",
                            keyboardService.getReplyKeyboard("Menu"));
                }

            default:
            return sendMessage(chatId,
                            "UNEXPECTED ERROR OCCURRED",
                            keyboardService.getReplyKeyboard("Menu"));
        }
    }

    public BotApiMethod handleMessage(Message message){
        final Long chatId = message.getChatId();
        final String text = message.getText();

        if (NumberUtils.isParsable(text.replaceAll("\\s+", ""))) {
            return sendMessage(chatId,
                    convert(chatId, text),
                    keyboardService.getReplyKeyboard(text));
        }
        return sendMessage(chatId,
                keyboardService.getMessage(text),
                keyboardService.getReplyKeyboard(text));

    }

    private EditMessageText editMessage(Message message, String text, InlineKeyboardMarkup keyboard){
        return EditMessageText.builder()
                .parseMode("HTML")
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(keyboard)
                .text(text)
                .build();
    }

    public SendMessage sendMessage(Long chatId, String text, ReplyKeyboard keyboard){
        return SendMessage.builder()
                .parseMode("HTML")
                .chatId(chatId.toString())
                .replyMarkup(keyboard)
                .text(text)
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

    private <G> String formMessageOfEntities(List<G> currencyDtos, String city){
        stringBuffer.append("Best courses in - ")
                    .append(city != null ? city : "NBRB")
                    .append('\n')
                    .append('\n');

        AtomicInteger i = new AtomicInteger();
        currencyDtos.forEach(c -> stringBuffer.append(i.incrementAndGet()).append(". ").append(c.toString()).append('\n'));

        String message = stringBuffer.toString();
        stringBuffer.setLength(0);
        return message;
    }
}

