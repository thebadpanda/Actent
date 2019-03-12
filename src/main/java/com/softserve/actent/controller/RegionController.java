package com.softserve.actent.controller;

import com.softserve.actent.constant.StringConstants;
import com.softserve.actent.model.dto.RegionDto;
import com.softserve.actent.model.dto.RegionUpdateDto;
import com.softserve.actent.model.entity.Region;
import com.softserve.actent.service.RegionService;
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
@RequestMapping("/api/v1")
public class RegionController {

    private final RegionService regionService;
    private final ModelMapper modelMapper;

    @Autowired
    public RegionController(RegionService regionService, ModelMapper modelMapper) {
        this.regionService = regionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/regions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegionDto get(@PathVariable @NotNull(message = StringConstants.REGION_ID_NOT_NULL)
                         @Positive(message = StringConstants.REGION_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {
        Region region = regionService.get(id);
        return modelMapper.map(region, RegionDto.class);
    }

    @GetMapping(value = "/regions")
    @ResponseStatus(HttpStatus.OK)
    public List<RegionDto> getAll(@RequestParam(value = "countryId", required = false)
                                  @Positive(message = StringConstants.COUNTRY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long countryId) {
        List<Region> regions;
        List<RegionDto> regionDtos;
        if (countryId == null) {
            regions = regionService.getAll();
        } else {
            regions = regionService.getByCountryId(countryId);
        }
        regionDtos = regions.stream()
                .map(region -> modelMapper.map(region, RegionDto.class))
                .collect(Collectors.toList());
        return regionDtos;
    }

    @PostMapping(value = "/regions")
    @ResponseStatus(HttpStatus.CREATED)
    public RegionDto add(@Validated @RequestBody RegionDto regionDto) {

        Region region = modelMapper.map(regionDto, Region.class);
        regionService.add(region);
        return regionDto;

    }

    @PutMapping(value = "/regions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RegionUpdateDto update(@PathVariable Long id,
                                  @Validated @RequestBody RegionUpdateDto regionDto) {
        Region region = regionService.update(modelMapper.map(regionDto, Region.class), id);
        return modelMapper.map(region, RegionUpdateDto.class);
    }


    @DeleteMapping(value = "/regions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull(message = StringConstants.COUNTRY_ID_NOT_NULL)
                       @Positive(message = StringConstants.COUNTRY_ID_MUST_BE_POSITIVE_AND_GREATER_THAN_ZERO) Long id) {
        regionService.delete(id);
    }
}

