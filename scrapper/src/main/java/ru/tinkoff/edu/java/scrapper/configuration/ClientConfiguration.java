package ru.tinkoff.edu.java.scrapper.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.clients.GithubWebClient;
import ru.tinkoff.edu.java.scrapper.clients.StackoverflowWebClient;

@Configuration(enforceUniqueMethods = false)
public class ClientConfiguration {
    private final ApplicationConfig applicationConfig;
    public ClientConfiguration(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }
    @Bean
    @Scope(value = "prototype")
    public GithubWebClient githubWebClient(@NotNull String baseUrl) {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(GithubWebClient.class);
    }
    @Bean
    @Scope(value = "prototype")
    public GithubWebClient githubWebClient() {
        WebClient webClient = WebClient.builder().baseUrl(applicationConfig.githubBaseUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(GithubWebClient.class);
    }
    @Bean
    @Scope(value = "prototype")
    public StackoverflowWebClient stackoverflowWebClient(@NotNull String baseUrl) {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(StackoverflowWebClient.class);
    }
    @Bean
    @Scope(value = "prototype")
    public StackoverflowWebClient stackoverflowWebClient() {
        WebClient webClient = WebClient.builder().baseUrl(applicationConfig.stackoverflowBaseUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(StackoverflowWebClient.class);
    }
}
