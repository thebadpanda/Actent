package com.softserve.actent.model.dto.converter;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.ValidationException;
import com.softserve.actent.model.dto.event.EventCreationDto;
import com.softserve.actent.model.entity.AccessType;
import com.softserve.actent.model.entity.Event;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventCreationConverter implements IModelMapperConverter<Event, EventCreationDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public EventCreationConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Event convertToEntity(EventCreationDto dto) {
        return extractEvent(dto);
    }

    @Override
    public EventCreationDto convertToDto(Event entity) {

        // NOP
        return null;
    }

    private Event extractEvent(EventCreationDto eventCreationDto) {

        Event event = modelMapper.map(eventCreationDto, Event.class);
        checkAndSetAccessType(event, eventCreationDto.getAccessType().toLowerCase());
        Location location = new Location();
        location.setId(eventCreationDto.getLocationId());
        event.setAddress(location);

        return event;
    }

    private void checkAndSetAccessType(Event event, String incomeType) {

        if (incomeType.equals("public")) {
            event.setAccessType(AccessType.PUBLIC);
        } else if (incomeType.equals("private")) {
            event.setAccessType(AccessType.PRIVATE);
        } else {
            throw new ValidationException(StringConstants.ACCESS_TYPE_CAN_NOT_BE_NULL_OR_EMPTY, ExceptionCode.VALIDATION_FAILED);
        }
    }
}
