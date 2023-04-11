package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import ru.tinkoff.edu.java.bot.clients.ScrapperClient;
import ru.tinkoff.edu.java.bot.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.ListLinksResponse;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ListCommandTest {
    @Mock
    private ScrapperClient scrapperClient;
    private Message message;
    private Long chatId;
    private String randomNonWhitespaceString(int length) {
        StringBuilder resultBuilder = new StringBuilder(length);
        for(int i = 0; i < length; ++i) {
            resultBuilder.append( (char)ThreadLocalRandom.current().nextInt(0x21, 0x7F) );
        }
        return resultBuilder.toString();
    }

    @BeforeEach
    public void setup() {
        this.message = new Message();
        Chat chat = new Chat();
        this.chatId = ThreadLocalRandom.current().nextLong(0L, 1000000000000000L);
        ReflectionTestUtils.setField(message, "text", "/list");
        ReflectionTestUtils.setField(chat, "id", chatId);
        ReflectionTestUtils.setField(message, "chat", chat);
    }

    @Test
    public void noTrackedLinks_listCommandResponse_returnsSpecialMessage() {
        when(scrapperClient.getLinks(eq(chatId))).thenReturn(ResponseEntity.ok(new ListLinksResponse(
                List.of(), 0
        )));

        ListCommand listCommand = new ListCommand(scrapperClient);
        String result = listCommand.response(message);

        assertEquals("No links yet. Start tracking one!", result);
    }

    @Test
    public void trackedLinksPresent_listCommandResponse_formatsLinksAsList() {
        int length1 = ThreadLocalRandom.current().nextInt(1, 30);
        int length2 = ThreadLocalRandom.current().nextInt(1, 30);
        String str1 = randomNonWhitespaceString(length1);
        String str2 = randomNonWhitespaceString(length2);
        when(scrapperClient.getLinks(eq(chatId))).thenReturn(ResponseEntity.ok(new ListLinksResponse(
                List.of(
                        new LinkResponse(
                                ThreadLocalRandom.current().nextLong(0L, 1000000000000000L),
                                str1
                        ),
                        new LinkResponse(
                                ThreadLocalRandom.current().nextLong(0L, 1000000000000000L),
                                str2
                        )
                ), 2
        )));

        ListCommand listCommand = new ListCommand(scrapperClient);
        String result = listCommand.response(message);

        assertEquals("1) %s\n2) %s\n".formatted(str1, str2), result);
    }
}
