package com.softserve.actent.service;

import java.util.List;

public interface BaseCrudService<E> {
    E add(E entity);

    E update(E entity, Long id);

    E get(Long id);

    List<E> getAll();

    void delete(Long id);
}