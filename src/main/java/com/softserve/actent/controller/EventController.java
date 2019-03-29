package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.validation.ValidationException;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.converter.EventConverter;
import com.softserve.actent.model.dto.converter.EventCreationConverter;
import com.softserve.actent.model.dto.event.EventCreationDto;
import com.softserve.actent.model.dto.event.EventDto;
import com.softserve.actent.model.dto.event.EventFilterDto;
import com.softserve.actent.model.dto.event.EventUpdateDto;
import com.softserve.actent.model.entity.Category;
import com.softserve.actent.model.entity.Event;
import com.softserve.actent.repository.EventFilterRepository;
import com.softserve.actent.service.EventService;
import com.softserve.actent.service.impl.CustomSpecification;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping(UrlConstants.API_V1)
public class EventController {

    private final EventService eventService;
    private final EventCreationConverter eventCreationConverter;
    private final EventConverter eventConverter;
    private final ModelMapper modelMapper;

    @Autowired
    EventFilterRepository eventFilterRepository;

    @Autowired
    public EventController(EventService eventService,
                           EventCreationConverter eventCreationConverter,
                           EventConverter eventConverter,
                           ModelMapper modelMapper) {

        this.eventService = eventService;
        this.eventCreationConverter = eventCreationConverter;
        this.eventConverter = eventConverter;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/events/all")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getAll() {

        List<Event> eventList = eventService.getAll();
        return eventConverter.convertToDto(eventList);
    }

    @GetMapping(value = "/events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto getEventById(@PathVariable
                                 @NotNull(message = StringConstants.EVENT_ID_CAN_NOT_BE_NULL)
                                 @Positive(message = StringConstants.EVENT_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
                                         Long id) {

        Event event = eventService.get(id);
        return eventConverter.convertToDto(event);
    }

    @GetMapping(value = "/events/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<EventDto> getByTitle(@PathVariable
                                     @NotNull(message = StringConstants.TITLE_SHOULD_NOT_BE_BLANK)
                                     @Length(message = StringConstants.TITLE_LENGTH_IS_TO_LONG)
                                             String title) {

        if (title == null || title.isEmpty()) {
            throw new ValidationException(StringConstants.TITLE_SHOULD_NOT_BE_BLANK, ExceptionCode.VALIDATION_FAILED);
        }

        List<Event> eventList = eventService.getByTitle(title);
        return eventConverter.convertToDto(eventList);
    }

    @PostMapping(value = "/events/filter")
    public List<EventDto> getByTitleOrAddressOrCategory(
            @RequestBody EventFilterDto eventFilterDto) {

        List<Event> result = eventFilterRepository.findAll(CustomSpecification.getTitle(eventFilterDto.getTitle())
                .and(CustomSpecification.getCategory(eventFilterDto.getCategoryId()))
                .and(CustomSpecification.getCity(eventFilterDto.getCityName()))
                .and(CustomSpecification.getDate(eventFilterDto.getDateFrom(), eventFilterDto.getDateTo())));

        return eventConverter.convertToDto(result);
    }

    @PostMapping(value = "/events")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addEvent(@Validated @RequestBody EventCreationDto eventCreationDto) {

        Event event = eventCreationConverter.convertToEntity(eventCreationDto);
        event = eventService.add(event);

        return new IdDto(event.getId());
    }

    @PutMapping(value = "events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateEventById(@Validated @RequestBody EventUpdateDto eventUpdateDto,
                                    @PathVariable
                                    @NotNull(message = StringConstants.EVENT_ID_CAN_NOT_BE_NULL)
                                    @Positive(message = StringConstants.EVENT_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
                                            Long id) {

        Event event = modelMapper.map(eventUpdateDto, Event.class);
        event = eventService.update(event, id);

        return eventConverter.convertToDto(event);
    }

    @DeleteMapping(value = "/events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEventById(@PathVariable
                                @NotNull(message = StringConstants.EVENT_ID_CAN_NOT_BE_NULL)
                                @Positive(message = StringConstants.EVENT_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
                                        Long id) {

        eventService.delete(id);
    }
}
