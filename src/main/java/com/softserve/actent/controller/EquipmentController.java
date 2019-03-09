package com.softserve.actent.controller;

import com.softserve.actent.model.dto.equipment.CreateEquipmentDto;
import com.softserve.actent.model.dto.equipment.EquipmentDto;
import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.repository.EventRepository;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.impl.EquipmentServiceImpl;
import com.softserve.actent.service.impl.EventServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class EquipmentController {

    private final EquipmentServiceImpl equipmentServiceImpl;

    private final UserRepository userRepository;

    private final EventServiceImpl eventService;

    private final ModelMapper modelMapper;

    @Autowired
    public EquipmentController(EquipmentServiceImpl equipmentServiceImpl, UserRepository userRepository, EventServiceImpl eventService, ModelMapper modelMapper) {
        this.equipmentServiceImpl = equipmentServiceImpl;
        this.userRepository = userRepository;
        this.eventService = eventService;
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
    public CreateEquipmentDto addEquipment(@RequestBody CreateEquipmentDto createEquipmentDto){

        Equipment newEquipment = modelMapper.map(createEquipmentDto, Equipment.class);

        Equipment equipment = equipmentServiceImpl.add(newEquipment);

        createEquipmentDto = modelMapper.map(equipment, CreateEquipmentDto.class);

        return createEquipmentDto;
    }

    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<Void> deleteEquipmentById(@PathVariable Long id){

        equipmentServiceImpl.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<CreateEquipmentDto> updateEquipmentById(@PathVariable Long id, @RequestBody CreateEquipmentDto createEquipmentDto){

        // TODO: use User and Event services for check if they exists
        Equipment equipment = equipmentServiceImpl.update(modelMapper.map(createEquipmentDto, Equipment.class), id);
        return new ResponseEntity<>(modelMapper.map(equipment, CreateEquipmentDto.class), HttpStatus.OK);
    }
}
