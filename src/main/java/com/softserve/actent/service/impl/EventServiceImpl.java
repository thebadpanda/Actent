package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Chat;
import com.softserve.actent.model.entity.ChatType;
import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.model.entity.Event;
import com.softserve.actent.model.entity.EventUser;
import com.softserve.actent.model.entity.Tag;
import com.softserve.actent.repository.CategoryRepository;
import com.softserve.actent.repository.ChatRepository;
import com.softserve.actent.repository.EventRepository;
import com.softserve.actent.repository.ImageRepository;
import com.softserve.actent.repository.LocationRepository;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.ChatService;
import com.softserve.actent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ChatService chatService;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ChatRepository chatRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            ChatService chatService,
                            UserRepository userRepository,
                            LocationRepository locationRepository,
                            CategoryRepository categoryRepository,
                            ImageRepository imageRepository,
                            ChatRepository chatRepository) {

        this.eventRepository = eventRepository;
        this.chatService = chatService;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
        this.chatRepository = chatRepository;
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

        if (event == null || id == null) {
            throwResourceNotFound();
        }

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

        Event eventFromBase = null;
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isPresent()) {

            eventFromBase = optionalEvent.get();
            addFeedbackIfExist(event, eventFromBase);
            setChatIfExist(event, eventFromBase);
            setImageIfExist(event, eventFromBase);
            setNewCategoryIfExist(event, eventFromBase);
            setNewLocationIfExist(event, eventFromBase);
            addNewCreatorIfExist(event, eventFromBase);
            addEventUserIfExist(event, eventFromBase);
            addNewTagsIfExist(event, eventFromBase);
            addNewEquipmentsIfExist(event, eventFromBase);
        }

        event.setCreationDate(eventFromBase != null ? eventFromBase.getCreationDate() : null);
        event.setId(id);

        return event;
    }

    private void addFeedbackIfExist(Event event, Event eventFromBase) {

        if (event.getFeedback() == null) {
            event.setFeedback(eventFromBase.getFeedback());
        } else {
            event.getFeedback().addAll(eventFromBase.getFeedback());
        }
    }

    private void setChatIfExist(Event event, Event eventFromBase) {

        if (event.getChat() == null) {
            event.setChat(eventFromBase.getChat());
        } else {
            if (!chatRepository.existsById(event.getChat().getId())) {
                throwResourceNotFound();
            }
        }
    }

    private void setImageIfExist(Event event, Event eventFromBase) {

        if (event.getImage() == null) {
            event.setImage(eventFromBase.getImage());
        } else {
            if (!imageRepository.existsById(event.getImage().getId())) {
                throwResourceNotFound();
            }
        }
    }

    private void setNewCategoryIfExist(Event event, Event eventFromBase) {

        if (event.getCategory() == null) {
            event.setCategory(eventFromBase.getCategory());
        } else {
            if (!categoryRepository.existsById(event.getCategory().getId())) {
                throwResourceNotFound();
            }
        }
    }

    private void setNewLocationIfExist(Event event, Event eventFromBase) {

        if (event.getAddress() == null) {
            event.setAddress(eventFromBase.getAddress());
        } else {
            if (!locationRepository.existsById(event.getAddress().getId())) {
                throwResourceNotFound();
            }
        }
    }

    private void addNewCreatorIfExist(Event event, Event eventFromBase) {

        if (event.getCreator() == null) {
            event.setCreator(eventFromBase.getCreator());
        } else {
            throwResourceNotFound();
        }
    }

    private void addEventUserIfExist(Event event, Event eventFromBase) {

        if (event.getEventUserList() == null) {
            event.setEventUserList(eventFromBase.getEventUserList());
        } else {

            List<EventUser> eventUserList = event.getEventUserList();
            List<EventUser> eventUserListFromBase = eventFromBase.getEventUserList();

            if (eventUserListFromBase != null) {
                for (EventUser eventUser : eventUserList) {
                    if (!eventUserListFromBase.contains(eventUser)) {
                        eventUserListFromBase.add(eventUser);
                    }
                }
                event.setEventUserList(eventUserListFromBase);
            }
        }
    }

    private void addNewTagsIfExist(Event event, Event eventFromBase) {

        if (event.getTags() == null) {
            event.setTags(eventFromBase.getTags());
        } else {

            List<Tag> tags = event.getTags();
            List<Tag> tagsFromBase = eventFromBase.getTags();

            if (tagsFromBase != null) {
                for (Tag tag : tags) {
                    if (!tagsFromBase.contains(tag)) {
                        tagsFromBase.add(tag);
                    }
                }
                event.setTags(tagsFromBase);
            }
        }
    }

    private void addNewEquipmentsIfExist(Event event, Event eventFromBase) {

        if (event.getEquipments() == null) {
            event.setEquipments(eventFromBase.getEquipments());
        } else {

            List<Equipment> equipments = event.getEquipments();
            List<Equipment> equipmentsFromBase = eventFromBase.getEquipments();

            if (equipmentsFromBase != null) {
                for (Equipment equipment : equipments) {
                    if (!equipmentsFromBase.contains(equipment)) {
                        equipmentsFromBase.add(equipment);
                    }
                }
                event.setEquipments(equipmentsFromBase);
            }
        }
    }

    private void checkIfExist(Long id) {

        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.EVENT_BY_THIS_ID_IS_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    private void checkEvent(Event event) {

        if (event == null || event.getCreator() == null || event.getAddress() == null || event.getCategory() == null || event.getAccessType() == null) {
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
