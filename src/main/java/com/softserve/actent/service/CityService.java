package com.softserve.actent.service;

import com.softserve.actent.model.entity.City;

import java.util.List;

public interface CityService extends BaseCrudService<City> {

    List<City> getAllByRegionId(Long regionId);
}

