package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс Telegram-бота, реализующий логику Long Polling и эхо-ответов.
 */
public class EchoBot extends TelegramLongPollingBot {

    private final MessageSender messageSender;

    /**
     * Конструктор бота с настройками подключения.
     *
     * @param options параметры бота, включая настройки прокси
     */
    public EchoBot(DefaultBotOptions options) {
        super(options);
        this.messageSender = new MessageSender(this);
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

    /**
     * Вложенный класс для отправки текстовых сообщений.
     */
    public static class MessageSender {

        private final AbsSender bot;

        /**
         * Конструктор компонента отправки сообщений.
         *
         * @param bot экземпляр бота для выполнения вызовов Telegram API
         */
        public MessageSender(AbsSender bot) {
            this.bot = bot;
        }

        /**
         * Отправляет текстовое сообщение в указанный чат.
         *
         * @param chatId уникальный идентификатор целевого чата
         * @param text   содержание отправляемого сообщения
         */
        public void sendTextMessage(long chatId, String text) {
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(text);

            try {
                bot.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
