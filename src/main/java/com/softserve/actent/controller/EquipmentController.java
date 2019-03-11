package com.softserve.actent.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.equipment.EquipmentCreateDto;
import com.softserve.actent.model.dto.equipment.EquipmentDto;
import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.impl.EquipmentServiceImpl;
import com.softserve.actent.service.impl.EventServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class EquipmentController {

    private final EquipmentServiceImpl equipmentServiceImpl;

    private final ModelMapper modelMapper;

    @Autowired
    public EquipmentController(EquipmentServiceImpl equipmentServiceImpl, ModelMapper modelMapper) {
        this.equipmentServiceImpl = equipmentServiceImpl;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/equipments")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipmentDto> getAllEquipments(){

        List<Equipment> equipments = equipmentServiceImpl.getAll();

        return equipments.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentDto getEquipmentById(@PathVariable Long id){

        Equipment equipment = equipmentServiceImpl.get(id);

        return modelMapper.map(equipment, EquipmentDto.class);
    }

    @PostMapping("/equipments")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addEquipment(@RequestBody EquipmentCreateDto equipmentCreateDto){

        Equipment equipment = equipmentServiceImpl.add(modelMapper.map(equipmentCreateDto, Equipment.class));
        return new IdDto(equipment.getId());
    }

    @DeleteMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEquipmentById(@PathVariable Long id){

        equipmentServiceImpl.delete(id);
    }

    @PutMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentCreateDto updateEquipmentById(@PathVariable Long id, @RequestBody EquipmentCreateDto equipmentCreateDto){

        Equipment equipment = equipmentServiceImpl.update(modelMapper.map(equipmentCreateDto, Equipment.class), id);
        return modelMapper.map(equipment, EquipmentCreateDto.class);
    }
}
