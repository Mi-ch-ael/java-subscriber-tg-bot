package ru.tinkoff.edu.java.scrapper.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.services.GithubWebService;

@Configuration
public class ClientConfiguration {
    @Bean
    public GithubWebService githubWebService(ApplicationConfig applicationConfig) {
        WebClient webClient = WebClient.builder().baseUrl(applicationConfig.githubBaseUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(GithubWebService.class);
    }
}
