package com.softserve.actent.model.dto.converter;

import com.softserve.actent.model.dto.message.CreateTextMessageDto;
import com.softserve.actent.model.entity.Message;
import com.softserve.actent.model.entity.MessageType;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextMessageConvert implements IModelMapperConverter<Message, CreateTextMessageDto> {

    private final
    ModelMapper modelMapper;

    @Autowired
    public TextMessageConvert(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Message convertToEntity(CreateTextMessageDto dto) {
        Message message = modelMapper.map(dto, Message.class);
        return message;
    }

    @Override
    public CreateTextMessageDto convertToDto(Message entity) {
        return modelMapper.map(entity, CreateTextMessageDto.class);
    }
}
