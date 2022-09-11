package org.example.telegramBot.service.keyboard.replyKeyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReplyKeyboard extends ReplyKeyboardMarkup {
    private final String keyboardName;

    AbstractReplyKeyboard(String keyboardName, List<String> buttons){
        this.keyboardName = keyboardName;
        final List<KeyboardRow> keyboardRows = new ArrayList<>(buttons.size());

        buttons.forEach(button -> {
            KeyboardRow keyboardRow = new KeyboardRow();
            if(button.equals("Меню")){
                keyboardRows.add(new KeyboardRow(List.of(KeyboardButton.builder()
                        .text(button)
                        .build())));
            }else {
                    keyboardRow.add(KeyboardButton.builder()
                            .text(button)
                            .build());
                }
            keyboardRows.add(keyboardRow);
        });
        super.setResizeKeyboard(true);
        super.setKeyboard(keyboardRows);
    }

    public abstract String getMessage();

    public String getKeyboardName() {
        return keyboardName;
    }
}
