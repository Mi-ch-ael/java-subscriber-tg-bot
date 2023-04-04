package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.sdkwrapper.Bot;

@Configuration
public class BotConfiguration {
    @Bean
    public Bot bot(ApplicationConfig applicationConfig) {
        return new Bot(applicationConfig.token());
    }
}
