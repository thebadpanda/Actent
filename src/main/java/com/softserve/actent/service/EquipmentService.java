package com.softserve.actent.service;

import com.softserve.actent.model.entity.Equipment;

public interface EquipmentService extends BaseCrudService<Equipment>{

    Boolean setSatisfied(Long equipmentId);
    Boolean setUnsatisfied(Long equipmentId);
    Equipment assignUser(Long userId, Long equipmentId);
}
