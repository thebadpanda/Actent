package com.softserve.actent.service;

import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Message;

import java.util.List;

public interface MessageService extends BaseCrudService<Message>{

    Message addImageMessage(Image image);

    List<Message> getAllMessagesByChatId(Long chatId);

}
