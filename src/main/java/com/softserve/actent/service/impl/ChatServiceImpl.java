package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.IncorrectChatTypeException;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.model.entity.ChatType;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.ChatRepository;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.ChatService;
import com.softserve.actent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, UserRepository userRepository, UserService userService) {
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Chat addChat(String type) {
        Chat chat = null;

        if (type.toLowerCase().equals("event")) {
            chat = new Chat();
            chat.setType(ChatType.EVENT);
            chatRepository.save(chat);
            return chat;
        }
        if (type.toLowerCase().equals("direct")) {
            chat = new Chat();
            chat.setType(ChatType.DIRECT);
            chatRepository.save(chat);
            return chat;
        } else {
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

    @Transactional
    @Override
    public void deleteChatById(Long chatId) {

        if (chatRepository.existsById(chatId)) {
            chatRepository.deleteById(chatId);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND,
                    ExceptionCode.CHAT_NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public Chat updateChat(Chat chat, Long chatId) {
        if (chatRepository.existsById(chatId)) {
            chat.setId(chatId);
            return chatRepository.save(chat);
        } else {
            throw new ResourceNotFoundException(ExceptionMessages.CHAT_BY_THIS_ID_IS_NOT_FOUND,
                    ExceptionCode.CHAT_NOT_FOUND);
        }
    }

    @Override
    public Chat banUserInChat(Long chatId, Long userId) {
        User user = userService.get(userId);
        Chat chat = getChatById(chatId);

        if(chat.getBannedUsers().contains(user)){
            throw new ResourceNotFoundException(StringConstants.USER_BY_SUCH_ID_IS_ALREADY_EXISTS_IN_LIST_OF_BANNED_USERS_IN_THIS_CHAT,
                    ExceptionCode.DUPLICATE_VALUE);
        }else{
            chat.getBannedUsers().add(user);
            chat = updateChat(chat, chatId);

            return chat;
        }
    }
}
