package ru.tinkoff.edu.java.bot.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;

@Configuration(enforceUniqueMethods = false)
public class ClientConfiguration {
    private final ApplicationConfig applicationConfig;
    public ClientConfiguration(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }
    @Bean
    @Scope(value = "prototype")
    ScrapperClient scrapperClient() {
        WebClient webClient = WebClient.builder().baseUrl(applicationConfig.scrapperBaseUrl()).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(ScrapperClient.class);
    }
    @Bean
    @Scope(value = "prototype")
    ScrapperClient scrapperClient(@NotNull String baseUrl) {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(ScrapperClient.class);
    }
}
