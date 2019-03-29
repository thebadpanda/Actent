package com.softserve.actent.service.impl;

import com.softserve.actent.model.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EventSpecification implements Specification<Event> {

    private Event filter;

    public EventSpecification(Event filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.disjunction();

        if (filter.getTitle() != null) {
            predicate.getExpressions().add(criteriaBuilder.like(root.get("title"), "%" + filter.getTitle() + "%"));
        }

        if (filter.getCategory().getName() != null) {

            predicate.getExpressions().add(criteriaBuilder.equal(root.get("category").get("name"), filter.getCategory().getName()));

        }

        return predicate;
    }
}
