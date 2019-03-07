package com.softserve.actent.controller;

import com.softserve.actent.model.dto.CreateEquipmentDto;
import com.softserve.actent.model.dto.EquipmentDto;
import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.model.entity.Event;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.EventRepository;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.impl.EquipmentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EquipmentController {

    private final EquipmentServiceImpl equipmentServiceImpl;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public EquipmentController(EquipmentServiceImpl equipmentServiceImpl, UserRepository userRepository, EventRepository eventRepository, ModelMapper modelMapper) {
        this.equipmentServiceImpl = equipmentServiceImpl;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/equipments")
    public ResponseEntity<List<EquipmentDto>> getAllEquipments(){

        List<Equipment> equipments = equipmentServiceImpl.getAllEquipments();

        List<EquipmentDto> equipmentDtos = equipments.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }

    @GetMapping("/equipments/{id}")
    public ResponseEntity<EquipmentDto> getEquipmentById(@PathVariable Long id){

        Equipment equipment = equipmentServiceImpl.getEquipmentById(id);
        EquipmentDto equipmentDto = modelMapper.map(equipment, EquipmentDto.class);

        return new ResponseEntity<>(equipmentDto, HttpStatus.OK);
    }

    @PostMapping("/equipments")
    public ResponseEntity<CreateEquipmentDto> addEquipment(@RequestBody CreateEquipmentDto createEquipmentDto){

        Equipment newEquipment = modelMapper.map(createEquipmentDto, Equipment.class);

        // TODO: use User and Event services for check if they exists
        User user = userRepository.getOne(createEquipmentDto.getUserid());
        Event event = eventRepository.getOne(createEquipmentDto.getEventid());

        newEquipment.setAssignedUser(user);
        newEquipment.setAssignedEvent(event);

        Equipment equipment = equipmentServiceImpl.addEquipment(newEquipment);

        createEquipmentDto = modelMapper.map(equipment, CreateEquipmentDto.class);
        createEquipmentDto.setUserid(equipment.getAssignedUser().getId());
        createEquipmentDto.setEventid(equipment.getAssignedEvent().getId());

        return new ResponseEntity<>(createEquipmentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<Void> deleteEquipmentById(@PathVariable Long id){

        equipmentServiceImpl.deleteEquipmentById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<CreateEquipmentDto> updateEquipmentById(@PathVariable Long id, @RequestBody CreateEquipmentDto createEquipmentDto){

        // TODO: use User and Event services for check if they exists
        Equipment equipment = equipmentServiceImpl.updateEquipmentById(id, modelMapper.map(createEquipmentDto, Equipment.class));
        return new ResponseEntity<>(modelMapper.map(equipment, CreateEquipmentDto.class), HttpStatus.OK);
    }
}
