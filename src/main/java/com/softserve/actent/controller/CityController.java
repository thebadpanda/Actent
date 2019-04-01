package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.location.CityDto;
import com.softserve.actent.model.dto.location.CityCreateDto;
import com.softserve.actent.model.dto.location.CityUpdateDto;
import com.softserve.actent.model.entity.City;
import com.softserve.actent.service.CityService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UrlConstants.API_V1_CITIES)
public class CityController {

    private final CityService cityService;
    private final ModelMapper modelMapper;

    @Autowired
    public CityController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityDto getById(@PathVariable @NotNull(message = StringConstants.CITY_ID_CAN_NOT_BE_NULL)
                           @Positive(message = StringConstants.CITY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {

        City city = cityService.get(id);
        return modelMapper.map(city, CityDto.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CityDto> getAll(@RequestParam(value = "regionId", required = false)
                                @Positive(message = StringConstants.CITY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long regionId) {

        List<City> cities;
        List<CityDto> cityDtos;
        if (regionId == null) {
            cities = cityService.getAll();
        } else {
            cities = cityService.getAllByRegionId(regionId);
        }
        cityDtos = cities.stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
        return cityDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto add(@Validated @RequestBody CityCreateDto cityCreateDto) {

        City city = cityService.add(modelMapper.map(checkCreatedCity(cityCreateDto), City.class));
        return new IdDto(city.getId());
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityUpdateDto updateName(@PathVariable Long id,
                                    @Validated @RequestBody CityUpdateDto cityUpdateDto) {

        City city = cityService.update(modelMapper.map(checkUpdatedCity(cityUpdateDto), City.class), id);
        return modelMapper.map(city, CityUpdateDto.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull(message = StringConstants.CITY_ID_CAN_NOT_BE_NULL)
                       @Positive(message = StringConstants.CITY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {

        cityService.delete(id);
    }

    private CityCreateDto checkCreatedCity(CityCreateDto cityCreateDto) {
        cityCreateDto.setName(cityCreateDto.getName().toLowerCase().trim());
        return cityCreateDto;
    }

    private CityUpdateDto checkUpdatedCity(CityUpdateDto cityUpdateDto) {
        cityUpdateDto.setName(cityUpdateDto.getName().toLowerCase().trim());
        return cityUpdateDto;
    }
}
