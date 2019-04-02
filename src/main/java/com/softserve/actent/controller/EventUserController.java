package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.converter.EventUserConverter;
import com.softserve.actent.model.dto.eventUser.EventUserDto;
import com.softserve.actent.model.entity.EventUser;
import com.softserve.actent.service.EventUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping(UrlConstants.API_V1)
@PreAuthorize("isAnonymous()")
public class EventUserController {

    private final EventUserService eventUserService;
    private final EventUserConverter eventsUsersConverter;

    @Autowired
    public EventUserController(EventUserService eventUserService, EventUserConverter eventUserConverter) {
        this.eventUserService = eventUserService;
        this.eventsUsersConverter = eventUserConverter;
    }

    @PostMapping(value = "/eventsUsers")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addEventUser(@Validated @RequestBody EventUserDto eventUserDto) {

        EventUser eventUser = eventsUsersConverter.convertToEntity(eventUserDto);
        return new IdDto(eventUserService.add(eventUser).getId());
    }

    @GetMapping(value = "/eventsUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<EventUserDto> getAllEventUser() {

        List<EventUser> eventUserList = eventUserService.getAll();
        return eventsUsersConverter.convertToDto(eventUserList);
    }

    @GetMapping(value = "/eventsUsers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventUserDto getEventUserById(@PathVariable
                                         @NotNull(message = StringConstants.EVENT_USER_ID_CAN_NOT_BE_NULL)
                                         @Positive(message = StringConstants.EVENT_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
                                                 Long id) {

        EventUser eventUser = eventUserService.get(id);
        return eventsUsersConverter.convertToDto(eventUser);
    }

    @GetMapping(value = "/eventsUsers/events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<EventUserDto> getEventUserByEventId(@PathVariable
                                                        @NotNull(message = StringConstants.EVENT_ID_CAN_NOT_BE_NULL)
                                                        @Positive(message = StringConstants.EVENT_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
                                                                Long id) {

        List<EventUser> eventUserList = eventUserService.getByEventId(id);
        return eventsUsersConverter.convertToDto(eventUserList);
    }

    @PutMapping(value = "/eventsUsers/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public EventUserDto updateEventsUsers(@Validated @RequestBody EventUserDto eventUserDto,
                                          @PathVariable
                                          @NotNull(message = StringConstants.EVENT_USER_ID_CAN_NOT_BE_NULL)
                                          @Positive(message = StringConstants.EVENT_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
                                                  Long id) {

        EventUser eventUser = eventsUsersConverter.convertToEntity(eventUserDto);
        eventUser = eventUserService.update(eventUser, id);
        return eventsUsersConverter.convertToDto(eventUser);
    }

    @DeleteMapping(value = "/eventsUsers/{id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable
                           @NotNull(message = StringConstants.EVENT_USER_ID_CAN_NOT_BE_NULL)
                           @Positive(message = StringConstants.EVENT_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO)
                                   Long id) {

        eventUserService.delete(id);
    }
}
