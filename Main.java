package org.example;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Главный класс приложения для инициализации и запуска Telegram-бота.
 */
public class Main {

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        try {
            DefaultBotOptions botOptions = new DefaultBotOptions();
            botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);
            botOptions.setProxyHost("127.0.0.1");
            botOptions.setProxyPort(10809);

            EchoBot bot = new EchoBot(botOptions);

            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);

            System.out.println("=== БОТ УСПЕШНО ЗАПУЩЕН! ===");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
