package com.softserve.actent.service;

import com.softserve.actent.model.entity.Message;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService extends BaseCrudService<Message> {

    Message addImageMessage(Message message);

    List<Message> getCurrentMessagesByChatId(Long chatId, Pageable pageable);

    List<Message> getMessagesByChatId(Long chatId);

}
