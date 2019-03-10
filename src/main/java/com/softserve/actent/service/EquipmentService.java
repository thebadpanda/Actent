package com.softserve.actent.service;

import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.model.entity.User;

public interface EquipmentService extends BaseCrudService<Equipment>{

    Boolean setSatisfied(Long id);
    Boolean setUnsatisfied(Long id);
    Equipment assignUser(User user, Long id);
}
