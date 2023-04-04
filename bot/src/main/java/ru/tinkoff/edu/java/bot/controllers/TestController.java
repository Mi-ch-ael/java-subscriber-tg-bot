package ru.tinkoff.edu.java.bot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.bot.sdkwrapper.Bot;

// todo: remove this file
@RestController
public class TestController {
    private final Bot bot;
    public TestController(Bot bot) {
        this.bot = bot;
    }
    /*@GetMapping(path = "/init")
    public void init() {
        this.bot.init();
    }*/
}
