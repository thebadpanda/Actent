package com.softserve.actent.service.impl;

import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.repository.EquipmentRepository;
import com.softserve.actent.service.EquipmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public Equipment addEquipment(Equipment equipment) {

        return equipmentRepository.save(equipment);
        // TODO: throw exception
    }

    @Override
    public List<Equipment> getAllEquipments() {

        log.info("In getAllEquipments method of EquipmentServiceImpl");
        return equipmentRepository.findAll();
        // TODO: throw exception

    }

    @Override
    public Equipment getEquipmentById(Long id) {

        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        return optionalEquipment.orElse(null);
        // TODO: or else throw exception

    }

    @Override
    public void deleteEquipmentById(Long id) {

        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if(optionalEquipment.isPresent()) {
            equipmentRepository.deleteById(id);
        }

        // TODO: else throw exception or so
    }

    @Override
    public Equipment updateEquipmentById(Long id, Equipment equipment) {

        if(equipmentRepository.findById(id).isPresent()){
            equipment.setId(id);
            return equipmentRepository.save(equipment);
        }else{

            // TODO: else throw exception or so
            return null;
        }
    }

}
