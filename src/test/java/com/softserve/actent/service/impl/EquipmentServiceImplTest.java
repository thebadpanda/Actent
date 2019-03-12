package com.softserve.actent.service.impl;

import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.model.entity.Equipment;
import com.softserve.actent.model.entity.Event;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.EquipmentRepository;
import com.softserve.actent.repository.EventRepository;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.EquipmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentServiceImplTest {

    @Autowired
    private EquipmentService equipmentService;

    @MockBean
    private EquipmentRepository equipmentRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private EventRepository eventRepository;

    private final Long existingId = 1L;
    private final Long secondExistingId = 2L;
    private final Long nonExistingId = -1L;
    private final String title = "title";
    private final String secondTitle = "secondTitle";
    private final String description = "description";
    private final Boolean satisfied = false;
    private final Integer equipmentCount = 2;


    @Before
    public void setUp() {

        Event event = new Event();
        User user = new User();

        Equipment equipment = new Equipment(existingId, title, description, satisfied, user, event);
        Equipment secondEquipment = new Equipment(secondExistingId, secondTitle, description, satisfied, user, event);

        List<Equipment> allEquipments = Arrays.asList(equipment, secondEquipment);

        Mockito.when(equipmentRepository.findById(existingId))
                .thenReturn(Optional.of(equipment));
        Mockito.when(equipmentRepository.findById(nonExistingId))
                .thenReturn(Optional.empty());
        Mockito.when(equipmentRepository.findAll())
                .thenReturn(allEquipments);
    }

    @Test
    public void whenValidId_thenEquipmentShouldBeFound() {

        Equipment found = equipmentService.get(existingId);
        verifyFindByIdIsCalledOnce();
        assertThat(found.getTitle()).isEqualTo(title);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void whenInValidId_thenEmployeeShouldNotBeFound(){

        Equipment found = equipmentService.get(nonExistingId);
        verifyFindByIdIsCalledOnce();
        assertThat(found).isNull();
    }

    @Test
    public void whenGetAll_thenListOfEquipmentsShouldBeReturned() {

        List<Equipment> founds = equipmentService.getAll();
        verifyFindAllEmployeesIsCalledOnce();
        assertThat(founds.size()).isEqualTo(equipmentCount);
        assertThat(founds.get(0).getTitle()).isEqualTo(title);
        assertThat(founds.get(1).getTitle()).isEqualTo(secondTitle);

    }

    private void verifyFindByIdIsCalledOnce() {

        Mockito.verify(equipmentRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }

    private void verifyFindAllEmployeesIsCalledOnce() {

        Mockito.verify(equipmentRepository, VerificationModeFactory.times(1)).findAll();
    }
}
