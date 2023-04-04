package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;

public class Bot implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final UserMessageProcessor messageProcessor;
    public Bot(String token) {
        this.telegramBot = new TelegramBot(token);
        this.telegramBot.setUpdatesListener(this, Throwable::printStackTrace);
        this.messageProcessor = new UserMessageProcessor(List.of(
                new HelpCommand(),
                new StartCommand(),
                new TrackCommand(),
                new UntrackCommand(),
                new ListCommand()
        ));
    }
    @Override
    public int process(List<Update> list) {
        for(Update update: list) {
            System.out.println(update);
            if(update != null && update.message() != null) {
                System.out.println(update.message().text());
                this.send(this.messageProcessor.process(update));
            }
        }
        return CONFIRMED_UPDATES_ALL;
    }
    public void send(SendMessage request) {
        SendResponse sendResponse = this.telegramBot.execute(request);
        if(!sendResponse.isOk()) {
            System.out.println("Error sending message: " + sendResponse.errorCode());
            System.out.println("Error sending message: " + sendResponse.description());
        }
        System.out.println(sendResponse.message());
    }
}
