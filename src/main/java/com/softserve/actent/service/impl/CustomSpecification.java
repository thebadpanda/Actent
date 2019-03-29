package com.softserve.actent.service.impl;


import com.softserve.actent.model.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public class CustomSpecification {


    public static Specification<Event> getTitle(String title) {
        return (Specification<Event>) (root, query, cb) -> {
            if (title == null || title.isEmpty()) {
                return null;
            } else {
                return cb.like(root.get("title"), "%" + title + "%");
            }
        };
    }

    public static Specification<Event> getCategory(List<Long> categoryId) {
        return (Specification<Event>) (root, query, cb) -> {
            if (categoryId.isEmpty()) {
                return null;
            } else {

                return cb.isTrue(root.get("category").get("id").in(categoryId));
            }
        };
    }

    public static Specification<Event> getCity(String city) {
        return (Specification<Event>) (root, query, cb) -> {
            if (city == null || city.isEmpty()) {
                return null;
            } else {
                return cb.equal(root.get("address").get("city").get("name"), city);
            }
        };
    }

    public static Specification<Event> getDate(Long dateFrom,Long dateTo ) {
        return (Specification<Event>) (root, query, cb) -> {
            if (dateFrom == null && dateTo == null || dateFrom.equals(0L)&& dateTo.equals(0L)) {
                return null;
            } else if(dateTo == null || dateTo.equals(0L)){
                return cb.greaterThanOrEqualTo(root.get("startDate"), dateFrom);
            } else if(dateFrom == null|| dateFrom.equals(0L)){
                return cb.lessThanOrEqualTo(root.get("startDate"), dateTo);
            } else{
                return cb.between(root.get("startDate"), dateFrom, dateTo);
            }
        };
    }

}
