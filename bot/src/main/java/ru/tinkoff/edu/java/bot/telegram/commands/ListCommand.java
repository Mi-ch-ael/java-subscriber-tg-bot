package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.ListLinksResponse;

import java.util.List;

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
    public String response(Message message) {
        try {
            ResponseEntity<ListLinksResponse> response = this.client.getLinks(message.chat().id());
            HttpStatus httpStatus = (HttpStatus) response.getStatusCode();
            return httpStatus.is2xxSuccessful() ? this.formatResponse(response.getBody()) :
                    "Error: scrapper returned code %s".formatted(httpStatus.toString());
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return "Could not fetch answer due to a fatal error: %s".formatted(
                    exception.getClass().getSimpleName()
            );
        }
    }

    private String formatResponse(ListLinksResponse listLinksResponse) {
        if(listLinksResponse.size() == 0) return "No links yet. Start tracking one!";
        StringBuilder responseBuilder = new StringBuilder();
        List<LinkResponse> list = listLinksResponse.links();
        for(int i = 0; i < listLinksResponse.size(); ++i) {
            responseBuilder.append("%d) %s\n".formatted(i+1, list.get(i).url()));
        }
        return responseBuilder.toString();
    }
}
