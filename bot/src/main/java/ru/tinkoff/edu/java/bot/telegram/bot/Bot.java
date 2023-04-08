package ru.tinkoff.edu.java.bot.telegram.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.telegram.commands.*;
import ru.tinkoff.edu.java.bot.telegram.processors.UserMessageProcessor;

import java.util.List;

public class Bot implements UpdatesListener {
    private final TelegramBot telegramBot;
    private final UserMessageProcessor messageProcessor;
    public Bot(String token, ScrapperClient client) {
        this.telegramBot = new TelegramBot(token);
        this.telegramBot.setUpdatesListener(this, Throwable::printStackTrace);
        this.messageProcessor = new UserMessageProcessor(List.of(
                new HelpCommand(client),
                new StartCommand(client),
                new TrackCommand(client),
                new UntrackCommand(client),
                new ListCommand(client)
        ));
    }
    @Override
    public int process(List<Update> list) {
        for(Update update: list) {
            if(update != null && update.message() != null) {
                this.send(this.messageProcessor.process(update));
            }
        }
        return CONFIRMED_UPDATES_ALL;
    }
    public void send(SendMessage request) {
        this.telegramBot.execute(request);
    }
}
