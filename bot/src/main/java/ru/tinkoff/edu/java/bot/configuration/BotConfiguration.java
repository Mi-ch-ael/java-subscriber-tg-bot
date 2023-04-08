package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;

@Configuration
public class BotConfiguration {
    @Autowired
    private BeanFactory beanFactory;
    @Bean
    public Bot bot(ApplicationConfig applicationConfig) {
        return new Bot(applicationConfig.token(),
                beanFactory.getBean("scrapperClient", ScrapperClient.class));
    }
}
