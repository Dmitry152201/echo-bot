package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Класс, отвечающий за формирование и отправку текстовых сообщений через Telegram API.
 */
public class SendTextMessage {

    private final AbsSender bot;

    /**
     * Конструктор компонента отправки сообщений.
     *
     * @param bot экземпляр бота для выполнения отправки сообщений
     */
    public SendTextMessage(AbsSender bot) {
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
