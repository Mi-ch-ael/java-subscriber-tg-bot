package ru.tinkoff.edu.java.scrapper.services;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.tinkoff.edu.java.scrapper.dto.GithubResponse;

@HttpExchange(accept = "application/vnd.github.v3+json")
public interface GithubWebClient {
    @GetExchange("/{username}/{repositoryName}")
    GithubResponse getRepositoryData(@PathVariable String username,
                                     @PathVariable String repositoryName);
}
