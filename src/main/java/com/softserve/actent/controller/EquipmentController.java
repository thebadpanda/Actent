package com.softserve.actent.controller;

import com.softserve.actent.constant.NumberConstants;
import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.equipment.EquipmentCreateDto;
import com.softserve.actent.model.dto.equipment.EquipmentDto;
import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.service.impl.EquipmentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Validated
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
    public List<EquipmentDto> getAllEquipments() {

        List<Equipment> equipments = equipmentServiceImpl.getAll();

        return equipments.stream()
                .map(equipment -> modelMapper.map(equipment, EquipmentDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentDto getEquipmentById(@PathVariable @NotNull
                                         @Min(value = NumberConstants.ID_MIN_VALUE, message = StringConstants.EQUIPMENT_ID_SHOULD_BE_POSITIVE) Long id) {

        Equipment equipment = equipmentServiceImpl.get(id);
        return modelMapper.map(equipment, EquipmentDto.class);
    }

    @PostMapping("/equipments")
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto addEquipment(@Validated @RequestBody EquipmentCreateDto equipmentCreateDto) {

        Equipment equipment = equipmentServiceImpl.add(modelMapper.map(equipmentCreateDto, Equipment.class));
        return new IdDto(equipment.getId());
    }

    @DeleteMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEquipmentById(@PathVariable @NotNull
                                    @Min(value = NumberConstants.ID_MIN_VALUE, message = StringConstants.EQUIPMENT_ID_SHOULD_BE_POSITIVE) Long id) {

        equipmentServiceImpl.delete(id);
    }

    @PutMapping("/equipments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentCreateDto updateEquipmentById(@PathVariable @NotNull
                                                  @Min(value = NumberConstants.ID_MIN_VALUE, message = StringConstants.EQUIPMENT_ID_SHOULD_BE_POSITIVE) Long id,
                                                  @Validated @RequestBody EquipmentCreateDto equipmentCreateDto) {

        Equipment equipment = equipmentServiceImpl.update(modelMapper.map(equipmentCreateDto, Equipment.class), id);
        return modelMapper.map(equipment, EquipmentCreateDto.class);
    }
}
