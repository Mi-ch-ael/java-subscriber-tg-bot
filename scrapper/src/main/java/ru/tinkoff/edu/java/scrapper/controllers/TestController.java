package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tinkoff.edu.java.scrapper.services.GithubWebService;
import ru.tinkoff.edu.java.scrapper.services.dto.GithubResponse;

// todo: remove this file
@RestController
public class TestController {
    @Autowired
    private GithubWebService githubWebService;
    @GetMapping(path = "/{username:[A-Za-z0-9_-]+}/{repoName:[A-Za-z0-9_-]+}")
    public GithubResponse test(@PathVariable String username, @PathVariable String repoName) {
        GithubResponse res = this.githubWebService.getRepositoryData(username, repoName);
        System.out.println(res);
        return res;
    }
}
