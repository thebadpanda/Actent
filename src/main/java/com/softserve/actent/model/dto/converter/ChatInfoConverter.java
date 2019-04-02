package com.softserve.actent.model.dto.converter;

import com.softserve.actent.model.dto.chat.ChatInfoDto;
import com.softserve.actent.model.dto.chat.UserForChatDto;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.repository.MessageRepository;
import com.softserve.actent.service.MessageService;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ChatInfoConverter implements IModelMapperConverter<Chat, ChatInfoDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Chat convertToEntity(ChatInfoDto dto) {
        return null;
    }

    @Override
    public ChatInfoDto convertToDto(Chat entity) {

        ChatInfoDto chatInfoDto = new ChatInfoDto();

        chatInfoDto.setId(entity.getId());
        chatInfoDto.setChatType(entity.getType());

        chatInfoDto.setCountOfMessages((long) messageRepository.findAllByChatId(entity.getId()).size());

        chatInfoDto.setBannedUserInChatDto(entity.getBannedUsers().stream()
                .map(user -> modelMapper.map(user, UserForChatDto.class))
                .collect(Collectors.toList()));

        return chatInfoDto;
    }
}
