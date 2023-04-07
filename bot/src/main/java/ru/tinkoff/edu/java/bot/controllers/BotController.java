package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

@RestController
public class BotController {
    @PostMapping(path = "/updates", consumes = "application/json")
    public void sendUpdates(@RequestBody LinkUpdateRequest linkUpdateRequest) {
        System.out.println(linkUpdateRequest);
    }
}
