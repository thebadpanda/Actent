package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.constant.UrlConstants;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.location.LocationCreateDto;
import com.softserve.actent.model.dto.location.LocationDto;
import com.softserve.actent.model.dto.location.LocationUpdateDto;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.service.LocationService;
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
@RequestMapping(UrlConstants.API_V1_LOCATIONS)
public class LocationController {

    private final LocationService locationService;
    private final ModelMapper modelMapper;

    @Autowired
    public LocationController(LocationService locationService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto getById(@PathVariable @NotNull(message = StringConstants.LOCATION_MUST_BE_NOT_NULL)
                               @Positive(message = StringConstants.LOCATION_ID_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {

        Location location = locationService.get(id);
        return modelMapper.map(location, LocationDto.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDto> getAll(@RequestParam(value = "cityId", required = false)
                                    @Positive(message = StringConstants.LOCATION_ID_POSITIVE_AND_GREATER_THAN_ZERO) Long cityId) {
        List<Location> locations;
        List<LocationDto> locationDtos;
        if (cityId == null) {
            locations = locationService.getAll();
        } else {
            locations = locationService.getAllByCityId(cityId);
        }
        locationDtos = locations.stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
        return locationDtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdDto add(@Validated @RequestBody LocationCreateDto locationCreateDto) {

        Location location = locationService.add(modelMapper.map(checkCreatedLocation(locationCreateDto), Location.class));
        return new IdDto(location.getId());
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationUpdateDto updateAddress(@PathVariable @NotNull(message = StringConstants.LOCATION_MUST_BE_NOT_NULL)
                                           @Positive(message = StringConstants.LOCATION_ID_POSITIVE_AND_GREATER_THAN_ZERO) Long id,
                                           @Validated @RequestBody LocationUpdateDto locationUpdateDto) {

        Location location = locationService.update(modelMapper.map(checkUpdatedLocation(locationUpdateDto), Location.class), id);
        return modelMapper.map(location, LocationUpdateDto.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull(message = StringConstants.LOCATION_MUST_BE_NOT_NULL)
                       @Positive(message = StringConstants.LOCATION_ID_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {

        locationService.delete(id);
    }

    private LocationCreateDto checkCreatedLocation(LocationCreateDto createDto) {
        createDto.setAddress(createDto.getAddress().toLowerCase().trim());
        return createDto;

    }

    private LocationUpdateDto checkUpdatedLocation(LocationUpdateDto locationUpdateDto) {
        locationUpdateDto.setAddress(locationUpdateDto.getAddress().toLowerCase().trim());
        return locationUpdateDto;
    }
}
