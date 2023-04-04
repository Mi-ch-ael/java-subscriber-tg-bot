package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.RemoveLinkRequest;

public class UntrackCommand extends AbstractCommand{
    public UntrackCommand(ScrapperClient client) {
        super(client);
    }

    @Override
    public String commandText() {
        return "/untrack <link>";
    }

    @Override
    public String commandRegex() {
        return "/untrack\\s+[^\\s]+";
    }

    @Override
    public String description() {
        return "Stop tracking a link";
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        String responseText = "Unknown command";
        if(this.supports(update)) {
            String link = message.text().split("\\s+")[1];
            try {
                ResponseEntity<LinkResponse> response = this.client.untrackLink(
                        message.chat().id(), new RemoveLinkRequest(link)
                );
                HttpStatusCode httpStatusCode = response.getStatusCode();
                responseText = httpStatusCode.equals(HttpStatusCode.valueOf(200)) ?
                        "Success. Untracked link `%s`.".formatted(link) :
                        "An error occurred: web scrapper returned code %s.".formatted(httpStatusCode);
            }
            catch (RuntimeException exception) {
                exception.printStackTrace();
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
