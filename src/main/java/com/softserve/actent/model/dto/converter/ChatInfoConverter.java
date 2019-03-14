package com.softserve.actent.model.dto.converter;

import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.model.dto.chat.ChatInfoDto;
import com.softserve.actent.model.dto.chat.UserForChatDto;
import com.softserve.actent.model.entity.Chat;
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
    private MessageService messageService;

    @Override
    public Chat convertToEntity(ChatInfoDto dto) {
        return null;
    }

    @Override
    public ChatInfoDto convertToDto(Chat entity) {

        ChatInfoDto chatInfoDto = new ChatInfoDto();

        chatInfoDto.setId(entity.getId());
        chatInfoDto.setChatType(entity.getType());

        try{
            chatInfoDto.setCountOfMessages(Long.valueOf(messageService.getAllMessagesByChatId(entity.getId()).size()));
        }catch (ResourceNotFoundException ex){
            chatInfoDto.setCountOfMessages(0L);
        }
        chatInfoDto.setBannedUserInChatDto(entity.getBannedUsers().stream()
                .map(user -> modelMapper.map(user, UserForChatDto.class))
                .collect(Collectors.toList()));

        return chatInfoDto;
    }
}
