package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс Telegram-бота, реализующий логику Long Polling и эхо-ответов.
 */
public class EchoBot extends TelegramLongPollingBot {

    private final SendTextMessage messageSender;

    /**
     * Конструктор бота с настройками подключения.
     *
     * @param options параметры бота, включая настройки прокси
     */
    public EchoBot(DefaultBotOptions options) {
        super(options);
        this.messageSender = new SendTextMessage(this);
    }

    /**
     * Возвращает имя пользователя бота в Telegram.
     *
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return "Khusainov_252201_echo_bot";
    }

    /**
     * Извлекает токен бота из переменных окружения системы.
     *
     * @return токен авторизации Telegram Bot API
     * @throws IllegalStateException если переменная окружения BOT_TOKEN не установлена
     */
    @Override
    public String getBotToken() {
        String token = System.getenv("BOT_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Переменная окружения BOT_TOKEN не найдена!");
        }
        return token;
    }

    /**
     * Обработчик входящих обновлений от Telegram API.
     *
     * @param update объект, содержащий данные о новом событии или сообщении
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            messageSender.sendTextMessage(chatId, messageText);
        }
    }
}
