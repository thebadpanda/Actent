package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectChatTypeException;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.model.entity.ChatType;
import com.softserve.actent.repository.ChatRepository;
import com.softserve.actent.service.ChatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

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
        else{
            log.error("Active with such type doesn't exists! Try another");
            throw new IncorrectChatTypeException(ExceptionMessages.ACTIVE_BY_THIS_TYPE_IS_NOT_FOUND,
                    ExceptionCode.INCORRECT_ACTIVITY_TYPE);
        }
    }

    @Override
    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId).
                orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND,
                        ExceptionCode.CHAT_NOT_FOUND));
    }

    @Override
    public void deleteChatById(Long chatId) {

        if(chatRepository.existsById(chatId)){
            chatRepository.deleteById(chatId);
        }else{
            throw new ResourceNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND,
                    ExceptionCode.CHAT_NOT_FOUND);
        }

    }


}
