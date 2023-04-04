package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
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
    public SendMessage handle(Update update) {
        Message message = update.message();
        String responseText = "Unknown command";
        if(this.supports(update)) {
            try {
                ResponseEntity<Void> response = this.client.registerChat(update.message().chat().id());
                responseText = response.getStatusCode() == HttpStatus.OK ?
                        "Start successful. Welcome!" :
                        "An error occurred: web scrapper returned code %s."
                                .formatted(response.getStatusCode());
            }
            catch (RuntimeException exception) {
                responseText = "Could not fetch answer due to a fatal error: %s".formatted(
                        exception.getClass().getSimpleName()
                );
            }
        }
        else if(this.next != null) {
            return this.next.handle(update);
        }
        return new SendMessage(message.chat().id(), responseText)
                .disableNotification(true);
    }
}
