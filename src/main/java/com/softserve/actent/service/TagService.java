package com.softserve.actent.service;

import com.softserve.actent.model.entity.Tag;

public interface TagService extends BaseCrudService<Tag> {

    Tag getByText(String name);
}
