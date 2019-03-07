package com.softserve.actent.service.impl;

import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.model.entity.ChatType;
import com.softserve.actent.repository.ChatRepository;
import com.softserve.actent.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    @Override
    public Chat addChat(String type) {
        Chat chat = null;

        if (type.toLowerCase().equals("event")){
            chat = new Chat();
            chat.setType(ChatType.EVENT);
            chatRepository.save(chat);
            return chat;
        }
        if(type.toLowerCase().equals("direct")){
            chat = new Chat();
            chat.setType(ChatType.DIRECT);
            chatRepository.save(chat);
            return chat;
        }
        return null;
    }

    @Override
    public Chat getChatById(Long chatId) {
        Optional<Chat> chatOptional = chatRepository.findById(chatId);
        return chatOptional.orElse(null);
    }

    @Override
    public void deleteChatById(Long chatId) {

        if(chatRepository.existsById(chatId)){
            chatRepository.deleteById(chatId);
        }else{
            //throw new exception
        }

    }


}
