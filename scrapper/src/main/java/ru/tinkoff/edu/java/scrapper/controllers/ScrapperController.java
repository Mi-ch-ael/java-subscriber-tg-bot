package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import ru.tinkoff.edu.java.scrapper.controllers.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.controllers.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.controllers.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.controllers.dto.RemoveLinkRequest;

import java.util.List;

@RestController
public class ScrapperController {
    @PostMapping(path = "/tg-chat/{id}")
    public void registerChat(@PathVariable long id) {
        System.out.println("Register chat #" + id);
    }

    @DeleteMapping(path = "/tg-chat/{id}")
    public void deleteChat(@PathVariable long id) {
        System.out.println("Delete chat #" + id);
        throw HttpClientErrorException.NotFound.create(HttpStatusCode.valueOf(404), "",
                HttpHeaders.EMPTY, new byte[0], null);
    }

    @GetMapping(path = "/links", produces = "application/json")
    public ListLinksResponse getWatchedLinks(@RequestParam long chatId) {
        return new ListLinksResponse(List.of(), 0);
    }

    @PostMapping(path = "/links", consumes = "application/json", produces = "application/json")
    public LinkResponse watchLink(@RequestParam long chatId, @RequestBody AddLinkRequest request) {
        return new LinkResponse(42000L, request.link());
    }

    @DeleteMapping(path = "/links", consumes = "application/json", produces = "application/json")
    public LinkResponse unwatchLink(@RequestParam long chatId,
                                    @RequestBody RemoveLinkRequest request) {
        return new LinkResponse(42000L, request.url());
    }
}
