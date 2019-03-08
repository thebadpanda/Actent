package com.softserve.actent.controller;

import com.softserve.actent.model.dto.CityDto;
import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.entity.City;
import com.softserve.actent.service.CityService;
import com.softserve.actent.service.RegionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CityController {

    private final CityService cityService;

    private final RegionService regionService;

    private final ModelMapper modelMapper;

    @Autowired
    public CityController(CityService cityService, RegionService regionService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.regionService = regionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/cities/{id}")
    public ResponseEntity<CityDto> get(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        City city = cityService.get(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @GetMapping(value = "/cities")
    public ResponseEntity<List<CityDto>> getAll(@RequestParam(value = "regionId", required = false) Long regionId) {
        List<City> cities;
        List<CityDto> cityDtos;
        if (regionId == null) {
            cities = cityService.getAll();
        } else {
            cities = cityService.getByRegionId(regionId);
        }
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityDtos = cities.stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/cities")
    public ResponseEntity<CityDto> add(@RequestBody CityDto cityDto) {
        City newCity = modelMapper.map(cityDto, City.class);
        newCity.setName(cityDto.getName());
        newCity.setRegion(regionService.get(cityDto.getRegionId()));

        City city = cityService.add(newCity);
        cityDto = modelMapper.map(city, CityDto.class);
        cityDto.setName(city.getName());
        cityDto.setRegionId(city.getRegion().getId());
        return new ResponseEntity<>(cityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/cities/{id}")
    public ResponseEntity<CityDto> update(@PathVariable Long id, @RequestBody CityDto cityDto) {
        City city = cityService.update(modelMapper.map(cityDto, City.class), id);
        return new ResponseEntity<>(modelMapper.map(city, CityDto.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/cities/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        City city = cityService.get(id);
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
