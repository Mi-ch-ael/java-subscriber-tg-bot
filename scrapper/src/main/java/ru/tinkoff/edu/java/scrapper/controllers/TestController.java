package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.services.GithubWebClient;
import ru.tinkoff.edu.java.scrapper.dto.GithubResponse;

// todo: remove this file
@RestController
public class TestController {
    @Autowired
    private GithubWebClient githubWebClient;
    @GetMapping(path = "/{username:[A-Za-z0-9_-]+}/{repoName:[A-Za-z0-9_-]+}")
    public GithubResponse test(@PathVariable String username, @PathVariable String repoName) {
        GithubResponse res = this.githubWebClient.getRepositoryData(username, repoName);
        System.out.println(res);
        return res;
    }
}
