package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.model.dto.CityDto;
import com.softserve.actent.model.dto.CityUpdateDto;
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
@RequestMapping(UrlConstants.API_V1)
public class CityController {

    private final CityService cityService;
    private final ModelMapper modelMapper;

    @Autowired
    public CityController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/cities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityDto get(@PathVariable @NotNull(message = StringConstants.CITY_ID_CAN_NOT_BE_NULL)
                       @Positive(message = StringConstants.CITY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {

        City city = cityService.get(id);
        return modelMapper.map(city, CityDto.class);
    }

    @GetMapping(value = "/cities")
    @ResponseStatus(HttpStatus.OK)
    public List<CityDto> getAll(@RequestParam(value = "regionId", required = false)
                                @Positive(message = StringConstants.CITY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long regionId) {

        List<City> cities;
        List<CityDto> cityDtos;
        if (regionId == null) {
            cities = cityService.getAll();
        } else {
            cities = cityService.getByRegionId(regionId);
        }
        cityDtos = cities.stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
        return cityDtos;
    }

    @PostMapping(value = "/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto add(@Validated @RequestBody CityDto cityDto) {
        City city = modelMapper.map(cityDto, City.class);
        cityService.add(city);
        return cityDto;
    }

    @PutMapping(value = "/cities/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityUpdateDto update(@PathVariable Long id,
                                @Validated @RequestBody CityUpdateDto cityDto) {
        City city = cityService.update(modelMapper.map(cityDto, City.class), id);
        return modelMapper.map(city, CityUpdateDto.class);
    }

    @DeleteMapping(value = "/cities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull(message = StringConstants.CITY_ID_CAN_NOT_BE_NULL)
                       @Positive(message = StringConstants.CITY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {
        cityService.delete(id);
    }
}
