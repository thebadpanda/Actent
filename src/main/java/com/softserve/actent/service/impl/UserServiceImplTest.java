package com.softserve.actent.service.impl;

import com.softserve.actent.model.entity.City;
import com.softserve.actent.model.entity.Image;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.model.entity.Role;
import com.softserve.actent.model.entity.Sex;
import com.softserve.actent.model.entity.User;
import com.softserve.actent.repository.UserRepository;
import com.softserve.actent.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private final String cityName = "Lviv";
    private final Long cityId = 1L;

    private final String firstUserEmail = "vasiliyutkin@gmail.com";
    private final String firstUserLogin = "vasya";
    private final String firstUserName = "vasiliy";
    private final String firstUserLastName = "utkin";
    private final String firstUserPassword = "qwerty";
    private final LocalDate firstUserBirthDate = LocalDate.parse("1977-12-03");
    private final Image firstUserAvatar = new Image();
    private final String firstUserBio = "About first user";
    private final Sex firstUserSex = Sex.MALE;
    private final Location firstUserLocation = new Location();
    private final Role firstUserRole = Role.USER;
    private final String firstUserImagePath = "D:IMG-7033";
    private final String firstUserImageHash = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    private final Long firstUserImageId = 1L;
    private final Long firstUserLocationId = 1L;
    private final String firstUserLocationAddress = "Fedkovycha str, 5";

    private final String secondUserEmail = "mamay@gmail.com";
    private final String secondUserLogin = "mamay";
    private final String secondUserName = "kozak";
    private final String secondUserLastName = "mamay";
    private final String secondUserPassword = "qwerty";
    private final LocalDate secondUserBirthDate = LocalDate.parse("1984-05-23");
    private final Image secondUserAvatar = new Image();
    private final String secondUserBio = "About kozak Mamay";
    private final Sex secondUserSex = Sex.MALE;
    private final Location secondUserLocation = new Location();
    private final Role secondUserRole = Role.USER;
    private final String secondUserImagePath = "D:IMG-7035";
    private final String secondUserImageHash = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4a";
    private final Long secondUserImageId = 2L;
    private final Long secondUserLocationId = 2L;
    private final String secondUserLocationAddress = "Pasternaka str, 77";


    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Before
    public void setUp() {

        City city = new City();
        city.setId(cityId);
        city.setName(cityName);

//        Image firstUserAvatar = new Image();
        firstUserAvatar.setFilePath(firstUserImagePath);
        firstUserAvatar.setHash(firstUserImageHash);
        firstUserAvatar.setId(firstUserImageId);

//        Image secondUserAvatar = new Image();
        firstUserAvatar.setFilePath(secondUserImagePath);
        firstUserAvatar.setHash(secondUserImageHash);
        firstUserAvatar.setId(secondUserImageId);


        firstUserLocation.setId(firstUserLocationId);
        firstUserLocation.setAddress(firstUserLocationAddress);
        firstUserLocation.setCity(city);

        secondUserLocation.setId(secondUserLocationId);
        secondUserLocation.setAddress(secondUserLocationAddress);
        secondUserLocation.setCity(city);

        User firstUser = new User();
        firstUser.setEmail(firstUserEmail);
        firstUser.setLogin(firstUserLogin);
        firstUser.setFirstName(firstUserName);
        firstUser.setLastName(firstUserLastName);
        firstUser.setPassword(firstUserPassword);

        User secondUser = new User();
        secondUser.setEmail(secondUserEmail);
        secondUser.setLogin(secondUserLogin);
        secondUser.setFirstName(secondUserName);
        secondUser.setLastName(secondUserLastName);
        secondUser.setPassword(secondUserPassword);

        User firstUserProfile = new User();
        firstUserProfile.setEmail(firstUserEmail);
        firstUserProfile.setLogin(firstUserLogin);
        firstUserProfile.setFirstName(firstUserName);
        firstUserProfile.setLastName(firstUserLastName);
        firstUserProfile.setBirthDate(firstUserBirthDate);
        firstUserProfile.setAvatar(firstUserAvatar);
        firstUserProfile.setBio(firstUserBio);
        firstUserProfile.setSex(firstUserSex);
        firstUserProfile.setLocation(firstUserLocation);
        firstUserProfile.setRole(firstUserRole);

        User secondUserProfile = new User();
        secondUserProfile.setEmail(secondUserEmail);
        secondUserProfile.setLogin(secondUserLogin);
        secondUserProfile.setFirstName(secondUserName);
        secondUserProfile.setLastName(secondUserLastName);
        secondUserProfile.setBirthDate(secondUserBirthDate);
        secondUserProfile.setAvatar(secondUserAvatar);
        secondUserProfile.setBio(secondUserBio);
        secondUserProfile.setSex(secondUserSex);
        secondUserProfile.setLocation(secondUserLocation);
        secondUserProfile.setRole(secondUserRole);



    }


}
