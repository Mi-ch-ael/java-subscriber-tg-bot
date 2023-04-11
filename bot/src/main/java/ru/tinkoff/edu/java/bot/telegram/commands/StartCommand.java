package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;

public class StartCommand extends AbstractCommand{
    public StartCommand(ScrapperClient client) {
        super(client);
    }

    @Override
    public String commandText() {
        return "/start";
    }

    @Override
    public String commandRegex() {
        return "/start";
    }

    @Override
    public String description() {
        return "Start using the bot by entering this command";
    }

    @Override
    public String response(Message message) {
        try {
            ResponseEntity<Void> response = this.client.registerChat(message.chat().id());
            HttpStatus httpStatus = (HttpStatus) response.getStatusCode();
            return httpStatus.is2xxSuccessful() ? "Registration successful." :
                    "Error: scrapper returned code %s".formatted(httpStatus.toString());
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return "Could not fetch answer due to a fatal error: %s".formatted(
                    exception.getClass().getSimpleName()
            );
        }
    }
}
