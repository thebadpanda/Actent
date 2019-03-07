package com.softserve.actent.model.dto.converter;

import com.softserve.actent.model.dto.message.ViewImageMessageDto;
import com.softserve.actent.model.dto.message.ViewMessageDto;
import com.softserve.actent.model.dto.message.ViewTextMessageDto;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.model.entity.MessageType;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewMessageConverter implements IModelMapperConverter<Message, ViewMessageDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public ViewMessageConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Message convertToEntity(ViewMessageDto dto) {
        return modelMapper.map(dto, Message.class);
    }

    @Override
    public ViewMessageDto convertToDto(Message entity) {
        if (entity.getMessageType().equals(MessageType.TEXT)) {
            return modelMapper.map(entity, ViewTextMessageDto.class);
        }
        else if (entity.getMessageType().equals(MessageType.IMAGE)) {
            return modelMapper.map(entity, ViewImageMessageDto.class);
        }
     //todo change return statement
    return null;
    }
}
