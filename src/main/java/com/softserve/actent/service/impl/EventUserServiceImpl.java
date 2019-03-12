package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.EventUser;
import com.softserve.actent.repository.EventUserRepository;
import com.softserve.actent.service.EventUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventUserServiceImpl implements EventUserService {

    private final EventUserRepository eventUserRepository;

    @Autowired
    public EventUserServiceImpl(EventUserRepository eventUserRepository) {
        this.eventUserRepository = eventUserRepository;
    }

    @Override
    @Transactional
    public EventUser add(EventUser entity) {

        if (entity.getEvent() == null || entity.getUser() == null) {
            throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }

        return eventUserRepository.save(entity);
    }

    @Override
    @Transactional
    public EventUser update(EventUser entity, Long id) {

        checkIfExist(id);
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

    private void checkIfExist(Long id) {

        if (!eventUserRepository.existsById(id)) {
            throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }
    }
}
