package com.softserve.actent.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.LocationDto;
import com.softserve.actent.model.dto.converter.LocationConverter;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.service.CityService;
import com.softserve.actent.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LocationController {

    private final LocationService locationService;

    private final LocationConverter locationConverter;

    private final CityService cityService;

    private final ModelMapper modelMapper;

    @Autowired
    public LocationController(LocationService locationService, LocationConverter locationConverter, CityService cityService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.locationConverter = locationConverter;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/locations/{id}")
    public ResponseEntity<LocationDto> get(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Location location = locationService.get(id);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(locationConverter.convertToDto(location), HttpStatus.OK);
    }

    @GetMapping(value = "/locations")
    public ResponseEntity<List<LocationDto>> getAll(@RequestParam(value = "cityId", required = false) Long cityId) {
        List<Location> locations;
        if (cityId == null) {
            locations = locationService.getAll();
        } else {
            locations = locationService.getByCityId(cityId);
        }
        if (locations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(locationConverter.convertToDto(locations), HttpStatus.OK);
    }

    @PostMapping(value = "/locations")
    public ResponseEntity<IdDto> add(@RequestBody LocationDto locationDto) {
        Location location = new Location();
        location.setAddress(locationDto.getAddress());
        location.setCity(cityService.get(locationDto.getCityId()));
        locationService.add(location);
        return new ResponseEntity<>(new IdDto(location.getId()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/locations/{id}")
    public ResponseEntity<IdDto> update(@PathVariable Long id, @RequestBody LocationDto locationDto) {
        Location location = locationService.update(modelMapper.map(locationDto, Location.class), id);
        return new ResponseEntity<>(new IdDto(location.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/locations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Location location = locationService.get(id);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.locationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}