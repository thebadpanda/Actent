package com.softserve.actent.model.dto.converter;

import com.softserve.actent.model.dto.eventUser.EventUserDto;
import com.softserve.actent.model.entity.EventUser;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventUserConverter implements IModelMapperConverter<EventUser, EventUserDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public EventUserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EventUser convertToEntity(EventUserDto dto) {

        return modelMapper.map(dto, EventUser.class);
    }

    @Override
    public EventUserDto convertToDto(EventUser entity) {

        EventUserDto eventUserDto = modelMapper.map(entity, EventUserDto.class);
        eventUserDto.setEventUserType(entity.getType());
        return eventUserDto;
    }
}
