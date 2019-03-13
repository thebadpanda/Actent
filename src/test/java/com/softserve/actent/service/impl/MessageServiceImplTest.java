package com.softserve.actent.service.impl;

import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.validation.MessageValidationException;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.model.entity.MessageType;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.MessageRepository;
import com.softserve.actent.service.MessageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepository messageRepository;

    private final Long firstId = 1L;
    private final Long secondId = 2L;
    private final Long nonExistingId = -1L;
    private final int messageCount = 2;
    private final Long chatId = 1L;
    private final String firstMessageContent = "firstMessageContent";
    private final String secondMessageContent = "secondMessageContent";
    List<Message> messages;
    private User user;
    private Chat chat;


    @Before
    public void setUp() {

        Message firstMessage = new Message();
        firstMessage.setId(firstId);
        firstMessage.setMessageContent(firstMessageContent);
        firstMessage.setMessageType(MessageType.TEXT);

        user = new User();
        chat = new Chat();
        user.setId(firstId);
        chat.setId(firstId);

        firstMessage.setSender(user);
        firstMessage.setChat(chat);

        Message secondMessage = new Message();
        secondMessage.setId(secondId);
        secondMessage.setMessageContent(secondMessageContent);
        secondMessage.setMessageType(MessageType.TEXT);

        messages = Arrays.asList(firstMessage, secondMessage);

        Mockito.when(messageRepository.findById(firstId)).thenReturn(Optional.of(firstMessage));
        Mockito.when(messageRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(messageRepository.findAllByChatId(chatId)).thenReturn(messages);

        Mockito.when(messageRepository.save(firstMessage)).thenReturn(firstMessage);
        Mockito.when(messageRepository.save(secondMessage)).thenReturn(secondMessage);

        Mockito.doNothing().when(messageRepository).deleteById(firstId);
        Mockito.doNothing().when(messageRepository).deleteById(secondId);

    }

    @Test
    public void whenValidId_thenMessageShouldBeFound() {
        Message message = messageService.get(firstId);
        assertThat(message.getMessageContent()).isEqualTo(firstMessageContent);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenInValidId_thenExceptionShouldBeThrown() {
        messageService.get(nonExistingId);
    }

    @Test
    public void given2Messages_findAllByChatId_thenReturn2Records() {
        assertThat(messageService.getAllMessagesByChatId(chatId).size()).isEqualTo(messageCount);
    }

    @Test
    public void whenAddMessage_thenMessageShouldBeReturned() {
        Message message = new Message();
        message.setId(secondId);
        message.setMessageContent(secondMessageContent);
        assertThat(messageService.add(message)).isEqualTo(message);
    }

    @Test
    public void whenUpdateMessage_thenMessageShouldBeReturned() {
        Message message = new Message();
        message.setId(firstId);
        message.setMessageContent(firstMessageContent);

        user = new User();
        chat = new Chat();
        user.setId(firstId);
        chat.setId(firstId);

        message.setSender(user);
        message.setChat(chat);
        assertThat(messageService.update(message, firstId)).isEqualTo(message);
    }

    @Test(expected = MessageValidationException.class)
    public void whenUpdateMessage_thenExceptionShouldBeThrown() {
        Message message = new Message();
        message.setId(firstId);
        message.setMessageContent(firstMessageContent);

        user = new User();
        chat = new Chat();
        user.setId(firstId);
        chat.setId(secondId);

        message.setSender(user);
        message.setChat(chat);
        assertThat(messageService.update(message, firstId)).isEqualTo(message);
    }

    @Test
    public void whenDeleteMessageWithExistingId_thenNothingShouldBeReturned() {

        messageService.delete(firstId);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenDeleteMessageWithNonExistingId_thenExceptionShouldBeThrown() {

        messageService.delete(nonExistingId);
    }

    @After
    public void tearDown() {
        user = null;
        chat = null;
        assertNull(user);
        assertNull(chat);
    }

}
