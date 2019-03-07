package com.softserve.actent.service;

import com.softserve.actent.model.entity.Event;

public interface EventService extends BaseCrudService<Event> {

    Event getByTitle(String title);
}