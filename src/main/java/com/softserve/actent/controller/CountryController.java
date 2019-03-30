package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.location.CountryCreateDto;
import com.softserve.actent.model.dto.location.CountryDto;
import com.softserve.actent.model.entity.Country;
import com.softserve.actent.service.CountryService;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UrlConstants.API_V1_COUNTRIES)
public class CountryController {

    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CountryController(CountryService countryService, ModelMapper modelMapper) {
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CountryDto getById(@PathVariable @NotNull(message = StringConstants.COUNTRY_ID_NOT_NULL)
                              @Positive(message = StringConstants.COUNTRY_ID_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {

        Country country = countryService.get(id);
        return modelMapper.map(country, CountryDto.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CountryDto> getAll() {
        List<Country> countries = countryService.getAll();

        return countries.stream()
                .map(country -> modelMapper.map(country, CountryDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto add(@Validated @RequestBody CountryCreateDto countryCreateDto) {

        Country country = countryService.add(modelMapper.map(checkCreatedCountry(countryCreateDto), Country.class));
        return new IdDto(country.getId());
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CountryCreateDto updateName(@PathVariable @NotNull(message = StringConstants.COUNTRY_ID_NOT_NULL)
                                       @Positive(message = StringConstants.COUNTRY_ID_POSITIVE_AND_GREATER_THAN_ZERO) Long id,
                                       @Validated @RequestBody CountryCreateDto countryCreateDto) {

        Country country = countryService.update(modelMapper.map(checkCreatedCountry(countryCreateDto), Country.class), id);
        return modelMapper.map(country, CountryCreateDto.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull(message = StringConstants.COUNTRY_ID_NOT_NULL)
                       @Positive(message = StringConstants.COUNTRY_ID_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {

        countryService.delete(id);
    }

    private CountryCreateDto checkCreatedCountry(CountryCreateDto countryCreateDto) {
        countryCreateDto.setName(countryCreateDto.getName().toLowerCase().trim());
        return countryCreateDto;
    }
}
