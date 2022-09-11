package org.example.telegramBot.service.keyboard.inlineKeyboard;

import org.example.telegramBot.service.enums.ECallbackAction;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractInlineKeyboard extends InlineKeyboardMarkup {
    private final ECallbackAction callBackAction;

    public AbstractInlineKeyboard(ECallbackAction keyboardName, List<String> buttons) {
        this.callBackAction = keyboardName;
        final List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>(buttons.size());

        buttons.forEach(buttonText -> {
            List<InlineKeyboardButton> buttonsList = getButtonsLine(buttonText);
            keyboardRows.add(buttonsList);
        });

        super.setKeyboard(keyboardRows);
    }

    public ECallbackAction getCallBackAction() {
        return callBackAction;
    }

    public abstract String getMessage();

    private List<InlineKeyboardButton> getButtonsLine(String text){
        if (callBackAction == ECallbackAction.CONVERT) {
            return List.of(
                    InlineKeyboardButton.builder()
                            .text(text)
                            .callbackData(String.join("|", callBackAction.toString(), "ORIGINAL", text))
                            .build(),

                    InlineKeyboardButton.builder()
                            .text(text)
                            .callbackData(String.join("|", callBackAction.toString(), "TARGET", text))
                            .build()
            );
        }
        return List.of(
                InlineKeyboardButton.builder()
                        .text(text)
                        .callbackData(callBackAction.toString() + "|" + text)
                        .build()
        );
    }
}
