package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.model.dto.LocationDto;
import com.softserve.actent.model.dto.LocationUpdateDto;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.service.LocationService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/locations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationDto get(@PathVariable @NotNull(message = StringConstants.LOCATION_MUST_BE_NOT_NULL)
                           @Positive(message = StringConstants.LOCATION_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {
        Location location = locationService.get(id);
        return modelMapper.map(location, LocationDto.class);
    }

    @GetMapping(value = "/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationDto> getAll(@RequestParam(value = "cityId", required = false)
                                    @Positive(message = StringConstants.LOCATION_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long cityId) {
        List<Location> locations;
        List<LocationDto> locationDtos;
        if (cityId == null) {
            locations = locationService.getAll();
        } else {
            locations = locationService.getByCityId(cityId);
        }
        locationDtos = locations.stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
        return locationDtos;
    }

    @PostMapping(value = "/locations")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto add(@Validated @RequestBody LocationDto locationDto) {

        Location location = modelMapper.map(locationDto, Location.class);
        locationService.add(location);
        return locationDto;
    }

    @PutMapping(value = "/locations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationUpdateDto update(@PathVariable Long id, @Validated @RequestBody LocationUpdateDto locationDto) {

        Location location = locationService.update(modelMapper.map(locationDto, Location.class), id);
        return modelMapper.map(location, LocationUpdateDto.class);
    }

    @DeleteMapping(value = "/locations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull(message = StringConstants.LOCATION_MUST_BE_NOT_NULL)
                       @Positive(message = StringConstants.LOCATION_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {
        locationService.delete(id);
    }
}
