package com.softserve.actent.service;

import com.softserve.actent.model.entity.Chat;

public interface ChatService {

    Chat addChat(String type);

    Chat getChatById(Long chatId);

}
