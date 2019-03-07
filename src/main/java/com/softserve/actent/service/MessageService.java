package com.softserve.actent.service;

import com.softserve.actent.model.dto.message.CreateTextMessageDto;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Message;

import java.util.List;

public interface MessageService {

    Message addTextMessage(Message message);

    Message addImageMessage(Image image);

    Message findById(Long id);

    List<Message> getMessages();

    void deleteMessageById(Long id);

    List<Message> getAllMessagesByChatId(Long chatId);

    Message updateMessage(Long id, CreateTextMessageDto createMessageDto);


}
