package com.softserve.actent.model.dto.converter;

import com.softserve.actent.model.entity.City;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityConverter implements IModelMapperConverter<City, CityDto> {
    private final ModelMapper modelMapper;

    @Autowired
    public CityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public City convertToEntity(CityDto cityDto) {
        City city = modelMapper.map(cityDto, City.class);
        return city;
    }

    @Override
    public CityDto convertToDto(City city) {
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        return cityDto;
    }
}
