package ru.tinkoff.edu.java.bot.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.tinkoff.edu.java.bot.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.ListLinksResponse;
import ru.tinkoff.edu.java.bot.dto.RemoveLinkRequest;

@HttpExchange
public interface ScrapperClient {
    @PostExchange("/tg-chat/{chatId}")
    ResponseEntity<Void> registerChat(@PathVariable Long chatId);
    @DeleteExchange("/tg-chat/{chatId}")
    ResponseEntity<Void> deleteChat(@PathVariable Long chatId);
    @GetExchange("/links")
    ResponseEntity<ListLinksResponse> getLinks(@RequestParam Long chatId);
    @PostExchange("/links")
    ResponseEntity<LinkResponse> trackLink(@RequestParam Long chatId, @RequestBody AddLinkRequest addLinkRequest);
    @DeleteExchange("/links")
    ResponseEntity<LinkResponse> untrackLink(@RequestParam Long chatId, @RequestBody RemoveLinkRequest removeLinkRequest);
}
