package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Event;
import com.softserve.actent.model.entity.EventUser;
import com.softserve.actent.repository.EventRepository;
import com.softserve.actent.repository.EventUserRepository;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.EventUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventUserServiceImpl implements EventUserService {

    private final EventUserRepository eventUserRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventUserServiceImpl(EventUserRepository eventUserRepository,
                                EventRepository eventRepository,
                                UserRepository userRepository) {

        this.eventUserRepository = eventUserRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public EventUser add(EventUser entity) {

        checkForCorrectAddedData(entity);
        return eventUserRepository.save(entity);
    }

    @Override
    @Transactional
    public EventUser update(EventUser entity, Long id) {

        checkIfExist(id);
        checkForCorrectAddedData(entity);
        entity.setId(id);
        return eventUserRepository.save(entity);
    }

    @Override
    public EventUser get(Long id) {

        return eventUserRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<EventUser> getAll() {

        return eventUserRepository.findAll();
    }

    @Override
    public void delete(Long id) {

        checkIfExist(id);
        eventUserRepository.deleteById(id);
    }
    
    @Override
    public List<EventUser> getByEventId(Long id) {

        checkEventExistence(id);
        Event event = new Event();
        event.setId(id);

        return eventUserRepository.findByEvent(event);
    }

    private void checkEventExistence(Long id) {

        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }
    }

    private void checkForCorrectAddedData(EventUser eventUser) {

        if (eventUser == null || eventUser.getEvent() == null || eventUser.getUser() == null || eventUser.getType() == null) {
            throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }

        if (!eventRepository.existsById(eventUser.getEvent().getId())) {
            throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }

        if (!userRepository.existsById(eventUser.getUser().getId())) {
            throw new ResourceNotFoundException(ExceptionMessages.USER_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }
    }

    private void checkIfExist(Long id) {

        if (!eventUserRepository.existsById(id)) {
            throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }
    }
}
