package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/tg-chat/{id}")
public class TgChatController {
    @PostMapping
    public void registerChat(@PathVariable long id) {
        System.out.println("Register chat #" + id);
    }

    @DeleteMapping
    public void deleteChat(@PathVariable long id) {
        System.out.println("Delete chat #" + id);
        throw HttpClientErrorException.NotFound.create(HttpStatusCode.valueOf(404), "",
                HttpHeaders.EMPTY, new byte[0], null);
    }
}
