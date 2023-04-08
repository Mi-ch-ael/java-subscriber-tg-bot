package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Message;
import org.springframework.http.HttpStatus;
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
    public String response(Message message) {
        String link = message.text().split("\\s+")[1];
        try {
            ResponseEntity<LinkResponse> response = this.client.untrackLink(
                    message.chat().id(), new RemoveLinkRequest(link)
            );
            HttpStatus httpStatus = (HttpStatus) response.getStatusCode();
            return httpStatus.is2xxSuccessful() ? this.formatResponse(response.getBody()) :
                    "Error: scrapper returned code %s".formatted(httpStatus.toString());
        }
        catch (RuntimeException exception) {
            exception.printStackTrace();
            return "Could not fetch answer due to a fatal error: %s".formatted(
                    exception.getClass().getSimpleName()
            );
        }
    }

    private String formatResponse(LinkResponse linkResponse) {
        return "Successfully untracked link %s".formatted(linkResponse.url());
    }
}
