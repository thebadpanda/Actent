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
@RequestMapping("/api/v1")
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

    @GetMapping(value = "/regions/{id}")
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


    @GetMapping(value = "/regions")
    public ResponseEntity<List<RegionDto>> getAll(@RequestParam(value = "countryId", required = false) Long countryId) {
        List<Region> regions;
        if (countryId == null) {
            regions = regionService.getAll();
        } else {
            regions = regionService.getByCountryId(countryId);
            if (regions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(regionConverter.convertToDto(regions), HttpStatus.CREATED);
    }

    @PostMapping(value = "/regions")
    public ResponseEntity<IdDto> add(@RequestBody RegionDto regionDto) {
        Region region = new Region();
        region.setName(regionDto.getName());
        region.setCountry(countryService.get(regionDto.getCountryId()));
        regionService.add(region);
        return new ResponseEntity<>(new IdDto(region.getId()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/regions/{id}")
    public ResponseEntity<IdDto> update(@PathVariable Long id, @RequestBody RegionDto regionDto) {
        Region region = regionService.get(id);
        if (region == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        modelMapper.map(regionDto, region);
        regionService.add(region);
        return new ResponseEntity<>(new IdDto(region.getId()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/regions/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Region region = regionService.get(id);
        if (region == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        regionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}