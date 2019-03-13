package com.softserve.actent.service.impl;

import com.softserve.actent.exceptions.DuplicateValueException;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.repository.CountryRepository;
import com.softserve.actent.service.CountryService;
import com.softserve.actent.model.entity.Country;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceImplTest {
    @Autowired
    private CountryService countryService;

    @MockBean
    private CountryRepository countryRepository;


    private final Long firstId = 1L;
    private final Long secondId = 2L;
    private final Long thirdId = 3L;
    private final Long notExistedId = 100L;
    private final String firstCountryName = "Ukraine";
    private final String secondCountryName = "Australia";
    private final String thirdCountryName = "Canada";
    private final int count = 3;
    private List<Country> countries;
    private Country firstCountry;
    private Country secondCountry;
    private Country thirdCountry;

    @Before
    public void setUp() {
        firstCountry = createCountry(firstId, firstCountryName);
        secondCountry = createCountry(secondId, secondCountryName);
        thirdCountry = createCountry(thirdId, thirdCountryName);

        countries = Arrays.asList(firstCountry, secondCountry,thirdCountry);

        Mockito.when(countryRepository.findById(firstId)).thenReturn(Optional.of(firstCountry));
        Mockito.when(countryRepository.findById(secondId)).thenReturn(Optional.of(secondCountry));
        Mockito.when(countryRepository.findById(thirdId)).thenReturn(Optional.of(thirdCountry));

        Mockito.when(countryRepository.findById(notExistedId)).thenReturn(Optional.empty());
        Mockito.when(countryRepository.findAll()).thenReturn(countries);

        Mockito.when(countryRepository.save(firstCountry)).thenReturn(firstCountry);
        Mockito.when(countryRepository.save(secondCountry)).thenReturn(secondCountry);
        Mockito.when(countryRepository.save(thirdCountry)).thenReturn(thirdCountry);

        Mockito.doNothing().when(countryRepository).deleteById(firstId);
        Mockito.doNothing().when(countryRepository).deleteById(secondId);
        Mockito.doNothing().when(countryRepository).deleteById(thirdId);
    }

    @Test
    public void testFindCountryById() {
        assertThat(countryService.get(thirdId).getName()).isEqualTo(thirdCountryName);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindByNotExistedId_orThrowException() {
        countryService.get(notExistedId);
    }

    @Test
    public void testFindAll() {
        assertThat(countryService.getAll().size()).isEqualTo(count);
        assertThat(countryService.getAll().get(0).getName()).isEqualTo(firstCountryName);
        assertThat(countryService.getAll().get(1).getName()).isEqualTo(secondCountryName);
        assertThat(countryService.getAll().get(2).getName()).isEqualTo(thirdCountryName);
    }

    @Test(expected = DuplicateValueException.class)
    public void testAddDuplicateCountryName_thenThrowException() {
        firstCountry = createCountry(firstId, firstCountryName);
        secondCountry = createCountry(secondId, firstCountryName);
        assertThat(countryService.add(firstCountry)).isEqualTo(secondCountry);
    }

    @Test(expected = DuplicateValueException.class)
    public void testUpdateCountryIdWithDuplicateCountryName_thenThrowException() {
        assertThat(countryService.update(secondCountry, secondId).getName()).isEqualTo(secondCountryName);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateCountryByNotExistedId_thenThrowException() {
        countryService.update(firstCountry, notExistedId);
    }

    @Test
    public void testDeleteCountryById() {
        countryService.delete(firstId);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteCountryByNotExistedId_thenThrowException() {
        countryService.delete(notExistedId);
    }

    private Country createCountry(Long countryId, String name) {
        Country country = new Country();
        country.setId(countryId);
        country.setName(name);
        return country;
    }

    @After
    public void tearDown() {
    }
}
