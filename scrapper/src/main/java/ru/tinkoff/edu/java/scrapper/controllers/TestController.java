package ru.tinkoff.edu.java.scrapper.controllers;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.clients.GithubWebClient;
import ru.tinkoff.edu.java.scrapper.clients.StackoverflowWebClient;
import ru.tinkoff.edu.java.scrapper.dto.GithubResponse;
import ru.tinkoff.edu.java.scrapper.dto.StackoverflowResponse;

// todo: remove this file
@RestController
public class TestController {
    @Autowired
    private BeanFactory beanFactory;
    private GithubWebClient githubWebClient = null;
    private StackoverflowWebClient stackoverflowWebClient = null;
    @GetMapping(path = "/{username:[A-Za-z0-9_-]+}/{repoName:[A-Za-z0-9_-]+}")
    public GithubResponse test(@PathVariable String username, @PathVariable String repoName) {
        if(this.githubWebClient == null) {
            this.githubWebClient = beanFactory.getBean(
                    GithubWebClient.class, "https://api.github.com"
            );
            //this.githubWebClient = beanFactory.getBean(GithubWebClient.class);
        }
        GithubResponse res = this.githubWebClient.getRepositoryData(username, repoName);
        System.out.println(res);
        return res;
    }

    @GetMapping(path = "/{id}")
    public StackoverflowResponse test2(@PathVariable String id) {
        System.out.println("About to send request to Stackoverflow");
        if(this.stackoverflowWebClient == null) {
            this.stackoverflowWebClient = beanFactory.getBean(StackoverflowWebClient.class);
        }
        StackoverflowResponse res = this.stackoverflowWebClient.getRepositoryData(id);
        System.out.println(res.items()[0]);
        return res;
    }
}
