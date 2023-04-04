package ru.tinkoff.edu.java.bot.sdkwrapper;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.ListLinksResponse;

import java.util.List;
import java.util.Objects;

public class ListCommand extends AbstractCommand{
    public ListCommand(ScrapperClient client) {
        super(client);
    }

    @Override
    public String commandText() {
        return "/list";
    }

    @Override
    public String commandRegex() {
        return "/list";
    }

    @Override
    public String description() {
        return "List currently tracked links";
    }

    @Override
    public SendMessage handle(Update update) {
        Message message = update.message();
        String responseText = "Unknown command";
        if(this.supports(update)) {
            try {
                ResponseEntity<ListLinksResponse> response = this.client.getLinks(message.chat().id());
                HttpStatusCode httpStatusCode = response.getStatusCode();
                if(httpStatusCode.isError()) {
                    responseText = "An error occurred: web scrapper returned status code %s"
                            .formatted(httpStatusCode);
                }
                else {
                    List<LinkResponse> links = Objects.requireNonNull(response.getBody()).links();
                    responseText = "";
                    for(LinkResponse link: links) {
                        responseText += "- %s\n".formatted(link.url());
                    }
                }
            }
            catch (RuntimeException exception) {
                responseText = "Could not fetch answer due to a fatal error: %s".formatted(
                        exception.getClass().getSimpleName()
                );
            }
            if(responseText.equals("")) {
                responseText = "No links yet. Start tracking one!";
            }
        }
        else if(this.next != null) {
            return this.next.handle(update);
        }
        return new SendMessage(message.chat().id(), responseText)
                .disableNotification(true);
    }
}
