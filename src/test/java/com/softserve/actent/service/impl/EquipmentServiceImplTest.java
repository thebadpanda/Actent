package com.softserve.actent.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**

*/

@RunWith(SpringRunner.class)
public class EquipmentServiceImplTest {

    @MockBean
    private static EquipmentRepository equipmentRepository;

    @MockBean
    private static UserRepository userRepository;

    @MockBean
    private static EventRepository eventRepository;

    @MockBean
    private static UserServiceImpl userServiceImpl;

    @TestConfiguration
    static class EquipmentServiceImplTestContextConfiguration {
        @Bean
        public EquipmentService equipmentService() {
            return new EquipmentServiceImpl(equipmentRepository, eventRepository, userRepository, userServiceImpl);
        }
    }

    @Autowired
    private EquipmentService equipmentService;

    @Before
    public void setUp() {

        Event event = new Event();
        User user = new User();
        Equipment equipment = new Equipment(1L, "Test", "description", true, user, event);

        Mockito.when(equipmentRepository.findById(1L))
                .thenReturn(Optional.of(equipment));
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void get() {

        String title = "Test";
        Equipment found = equipmentService.get(1L);
        assertEquals(found.getTitle(),title);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void delete() {
    }
}