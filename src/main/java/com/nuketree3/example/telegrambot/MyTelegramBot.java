package com.nuketree3.example.telegrambot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String botUsername;

    public MyTelegramBot(String botToken, String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

        public MyTelegramBot() {
        this.botToken = null;
        this.botUsername = null;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update);
        }
    }

    private void handleMessage(Update update) {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        if (messageText.equals("/start")) {
            sendWelcomeMessage(chatId);
        }
    }

    private void handleCallbackQuery(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();

        switch (callbackData) {
            case "show_more_buttons":
                sendAdditionalButtons(chatId);
                break;
            case "back_to_main":
                sendWelcomeMessage(chatId);
                break;
            default:
                break;
        }

        try {
            execute(new AnswerCallbackQuery(update.getCallbackQuery().getId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendWelcomeMessage(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Добро пожаловать! Выберите опцию:");

        InlineKeyboardMarkup keyboardMarkup = createMainKeyboard();
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendAdditionalButtons(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите одну из дополнительных опций:");

        InlineKeyboardMarkup keyboardMarkup = createAdditionalKeyboard();
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardMarkup createMainKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton chooseButton = new InlineKeyboardButton();
        chooseButton.setText("Выбрать и забронировать рабочее место");
        chooseButton.setUrl("https://dikidi.net/g405039?p=0.sp&fbclid=PAQ0xDSwLQoKhleHRuA2FlbQIxMQABpzZrGgA8SL94BStY6pAQqZi2_fiTF5rkWAkB8MJDSBCJBqa3VXcFFdHZpNL5_aem_yd_7Vw8KrACF6AWztq5GZg");
        row1.add(chooseButton);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton feedbackButton = new InlineKeyboardButton();
        feedbackButton.setText("Оставить отзыв");
        feedbackButton.setUrl("https://yandex.ru/maps/org/kam_family/168138221094/?indoorLevel=1&ll=30.342926%2C60.071461&z=17.06");
        row2.add(feedbackButton);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton contactButton = new InlineKeyboardButton();
        contactButton.setText("Связаться с администратором");
        contactButton.setUrl("https://wa.me/79893365540");
        row3.add(contactButton);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        InlineKeyboardButton moreButton = new InlineKeyboardButton();
        moreButton.setText("Дополнительные опции");
        moreButton.setCallbackData("show_more_buttons");
        row4.add(moreButton);

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    private InlineKeyboardMarkup createAdditionalKeyboard() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Мы в ВК");
        button1.setUrl("https://vk.com/kamfamily_spb_beautycoworking");
        row1.add(button1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Мы в Телеграмме");
        button2.setUrl("https://t.me/KamFamilybk-0");
        row2.add(button2);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Мы в Инстаграме");
        button3.setUrl("https://www.instagram.com/kam_family_spb?igsh=Y2ptOWhzeHVxNXBz&utm_source=qr");
        row3.add(button3);

        List<InlineKeyboardButton> row4 = new ArrayList<>();
        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText("← Назад");
        backButton.setCallbackData("back_to_main");
        row4.add(backButton);

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
