package ru.tinkoff.edu.java.bot.telegram.processors;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.tinkoff.edu.java.bot.telegram.commands.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UnknownCommandTest {
    private Update update;
    private Long chatId;

    @BeforeEach
    public void setup() {
        this.update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        this.chatId = ThreadLocalRandom.current().nextLong(0L, 1000000000000000L);
        ReflectionTestUtils.setField(this.update, "message", message);
        ReflectionTestUtils.setField(message, "chat", chat);
        ReflectionTestUtils.setField(chat, "id", this.chatId);
    }

    @Test
    public void allCommandHandlers_unknownCommandEntered_unknownCommandMessageIsReturned() {
        List<Command> orderedCommands = List.of(
                new HelpCommand(null),
                new StartCommand(null),
                new TrackCommand(null),
                new UntrackCommand(null),
                new ListCommand(null)
        );
        UserMessageProcessor userMessageProcessor = new UserMessageProcessor(orderedCommands);

        ReflectionTestUtils.setField(this.update.message(), "text", "/unknown");
        SendMessage sendMessage = userMessageProcessor.process(this.update);

        Map<String, Object> parameters = (Map<String, Object>)
                ReflectionTestUtils.getField(sendMessage, "parameters");
        assertEquals(this.chatId, (Long) parameters.get("chat_id"));
        assertEquals("Unknown command", parameters.get("text"));
        assertTrue((Boolean) parameters.get("disable_notification"));
    }
}
