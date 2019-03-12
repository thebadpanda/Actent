package com.softserve.actent.controller;

import com.softserve.actent.model.dto.CountryDto;
import com.softserve.actent.model.entity.Country;
import com.softserve.actent.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/countries/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto get(@PathVariable Long id) {

        Country country = countryService.get(id);
        return modelMapper.map(country, CountryDto.class);
    }

    @GetMapping(value = "/countries")
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDto> getAll() {

        List<Country> countries = countryService.getAll();
        List<CountryDto> countryDtos = countries.stream()
                .map(country -> modelMapper.map(country, CountryDto.class))
                .collect(Collectors.toList());
        return countryDtos;
    }

    @PostMapping(value = "/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto add(@Validated @RequestBody CountryDto countryDto) {

        Country country = modelMapper.map(countryDto, Country.class);
        countryService.add(country);
        return countryDto;
    }

    @PutMapping(value = "/countries/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto update(@PathVariable Long id, @Validated @RequestBody CountryDto countryDto) {

        Country country = countryService.update(modelMapper.map(countryDto, Country.class), id);
        return modelMapper.map(country, CountryDto.class);
    }

    @DeleteMapping(value = "/countries/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        countryService.delete(id);
    }
}
