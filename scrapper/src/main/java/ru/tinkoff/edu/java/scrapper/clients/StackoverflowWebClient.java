package ru.tinkoff.edu.java.scrapper.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.tinkoff.edu.java.scrapper.dto.StackoverflowResponse;

@HttpExchange
public interface StackoverflowWebClient {
    @GetExchange("/{questionId}?site=stackoverflow")
    StackoverflowResponse getRepositoryData(@PathVariable String questionId);
}
