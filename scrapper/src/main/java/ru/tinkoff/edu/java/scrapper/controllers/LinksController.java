package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;

import java.util.List;

@RestController
@RequestMapping("/links")
public class LinksController {
    @GetMapping(produces = "application/json")
    public ListLinksResponse getWatchedLinks(@RequestParam long chatId) {
        return new ListLinksResponse(List.of(), 0);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public LinkResponse watchLink(@RequestParam long chatId, @RequestBody AddLinkRequest request) {
        return new LinkResponse(42000L, request.link());
    }

    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public LinkResponse unwatchLink(@RequestParam long chatId,
                                    @RequestBody RemoveLinkRequest request) {
        return new LinkResponse(42000L, request.url());
    }
}
