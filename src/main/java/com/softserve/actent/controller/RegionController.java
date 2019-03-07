package com.softserve.actent.controller;

import com.softserve.actent.model.dto.IdDto;
import com.softserve.actent.model.dto.RegionDto;
import com.softserve.actent.model.dto.converter.RegionConverter;
import com.softserve.actent.model.entity.Region;
import com.softserve.actent.service.CountryService;
import com.softserve.actent.service.RegionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

    private final RegionService regionService;

    private final RegionConverter regionConverter;

    private final ModelMapper modelMapper;

    private final CountryService countryService;

    @Autowired
    public RegionController(RegionService regionService, RegionConverter regionConverter, ModelMapper modelMapper, CountryService countryService) {
        this.regionService = regionService;
        this.regionConverter = regionConverter;
        this.modelMapper = modelMapper;
        this.countryService = countryService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RegionDto> get(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Region region = regionService.get(id);
        if (region == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(regionConverter.convertToDto(region), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RegionDto>> getAll() {
        List<Region> regions = regionService.getAll();
        if (regions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(regionConverter.convertToDto(regions), HttpStatus.OK);
    }

    @GetMapping(value = "/countries/{countryId}")
    public ResponseEntity<List<RegionDto>> getAllInCountry(@PathVariable Long countryId) {
        List<Region> regions = regionService.getByCountryId(countryId);
        if (regions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(regionConverter.convertToDto(regions), HttpStatus.CREATED);
    }

    @PostMapping(value = "/country/{countryId}")
    public ResponseEntity<IdDto> add(@PathVariable Long countryId, @RequestBody RegionDto regionDto) {
        Region region = new Region();
        region.setName(regionDto.getName());
        region.setCountry(countryService.get(countryId));
        regionService.add(region);
        return new ResponseEntity<>(new IdDto(region.getId()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IdDto> update(@PathVariable Long id, @RequestBody RegionDto regionDto) {
        Region region = regionService.get(id);
        if (region == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        modelMapper.map(regionDto, region);
        regionService.add(region);
        return new ResponseEntity<>(new IdDto(region.getId()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Region region = regionService.get(id);
        if (region == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        regionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}