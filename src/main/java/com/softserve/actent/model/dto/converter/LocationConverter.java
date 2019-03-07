package com.softserve.actent.model.dto.converter;

import com.softserve.actent.model.dto.LocationDto;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationConverter implements IModelMapperConverter<Location, LocationDto> {
    private final ModelMapper modelMapper;

    @Autowired
    public LocationConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Location convertToEntity(LocationDto locationDto) {
        Location location = modelMapper.map(locationDto, Location.class);
        return location;
    }

    @Override
    public LocationDto convertToDto(Location location) {
        LocationDto locationDto = modelMapper.map(location, LocationDto.class);
        return locationDto;
    }
}