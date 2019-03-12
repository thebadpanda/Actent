package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.model.entity.ChatType;
import com.softserve.actent.model.entity.Event;
import com.softserve.actent.repository.CategoryRepository;
import com.softserve.actent.repository.EventRepository;
import com.softserve.actent.repository.LocationRepository;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.ChatService;
import com.softserve.actent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ChatService chatService;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            ChatService chatService,
                            UserRepository userRepository,
                            LocationRepository locationRepository,
                            CategoryRepository categoryRepository) {

        this.eventRepository = eventRepository;
        this.chatService = chatService;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Event add(Event event) {

        checkEvent(event);
        return getSavedEvent(event);
    }

    @Override
    public Event get(Long id) {

        return eventRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND,
                        ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<Event> getAll() {

        return eventRepository.findAll();
    }

    @Override
    public List<Event> getByTitle(String title) {

        List<Event> events = eventRepository.findByTitle(title);

        if (events == null) {
            throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_TITLE_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
        }

        return events;
    }

    @Override
    public Event update(Event event, Long id) {

        Event preparedEvent = getPreparedEventFromDataBase(event, id);
        return getUpdatedEvent(preparedEvent);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        checkIfExist(id);
        eventRepository.deleteById(id);
    }

    @Transactional
    protected Event getSavedEvent(Event event) {

        event.setChat(createChat());
        return eventRepository.save(event);
    }

    @Transactional
    protected Event getUpdatedEvent(Event event) {

        return eventRepository.save(event);
    }

    private Chat createChat() {

        return chatService.addChat(ChatType.EVENT.toString());
    }

    private Event getPreparedEventFromDataBase(Event event, Long id) {

        checkIfExist(id);

        event.setId(id);
        return event;
    }

    private void checkIfExist(Long id) {

        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    private void checkEvent(Event event) {

        if (event.getCreator() == null || event.getAddress() == null || event.getCategory() == null || event.getAccessType() == null) {
            throwResourceNotFound();
        } else if (!userRepository.existsById(event.getCreator().getId())) {
            throwResourceNotFound();
        } else if (!locationRepository.existsById(event.getAddress().getId())) {
            throwResourceNotFound();
        } else if (!categoryRepository.existsById(event.getCategory().getId())) {
            throwResourceNotFound();
        }
    }

    private void throwResourceNotFound() {
        throw new ResourceNotFoundException(ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND, ExceptionCode.NOT_FOUND);
    }
}
