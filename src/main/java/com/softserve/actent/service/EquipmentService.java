package com.softserve.actent.service;

import com.softserve.actent.model.entity.Equipment;

import java.util.List;

public interface EquipmentService {

    List<Equipment> getAllEquipments();

    Equipment addEquipment(Equipment equipment);

    Equipment getEquipmentById(Long id);

    void deleteEquipmentById(Long id);

    Equipment updateEquipmentById(Long id, Equipment equipment);
}
