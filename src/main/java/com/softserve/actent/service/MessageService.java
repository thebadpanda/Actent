package com.softserve.actent.service;

import com.softserve.actent.model.entity.Message;

import java.util.List;

public interface MessageService extends BaseCrudService<Message>{

    Message addImageMessage(Message message);

    List<Message> getAllMessagesByChatId(Long chatId);

}
