package com.softserve.actent.model.dto.converter;

import com.softserve.actent.model.dto.RegionDto;
import com.softserve.actent.model.entity.Region;
import com.softserve.actent.utils.modelmapper.IModelMapperConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionConverter implements IModelMapperConverter<Region, RegionDto> {
    private final ModelMapper modelMapper;

    @Autowired
    public RegionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Region convertToEntity(RegionDto regionDto) {
        Region region=modelMapper.map(regionDto,Region.class);
        return region;
    }

    @Override
    public RegionDto convertToDto(Region region) {
        RegionDto regionDto=modelMapper.map(region,RegionDto.class);
        return regionDto;
    }
}