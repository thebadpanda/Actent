package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.DuplicateValueException;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.City;
import com.softserve.actent.repository.CityRepository;
import com.softserve.actent.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final RegionServiceImpl regionService;

    @Transactional
    @Override
    public City add(City city) {
        City newCity = new City();
        newCity.setName(city.getName());
        newCity.setRegion(city.getRegion());

        if (isCityAlreadyExistInDatabase(newCity.getName(), getRegionIdInCity(newCity))) {
            throw new DuplicateValueException(
                    ExceptionMessages.REGION_ALREADY_EXIST,
                    ExceptionCode.DUPLICATE_VALUE);
        } else {
            return cityRepository.save(city);
        }
    }

    @Transactional
    @Override
    public City update(City city, Long cityId) {
        if (cityRepository.existsById(cityId)) {
            City dbCity = cityRepository.getOne(cityId);

            if (isCityAlreadyExistInDatabase(city.getName(), getRegionIdInCity(dbCity))
                    && !city.getName().equals(dbCity.getName())) {
                throw new DuplicateValueException(
                        ExceptionMessages.CITY_ALREADY_EXIST,
                        ExceptionCode.DUPLICATE_VALUE);
            }
            dbCity.setName(city.getName());
            return cityRepository.save(dbCity);

        } else {
            throw new ResourceNotFoundException(
                    ExceptionMessages.REGION_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public City get(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ExceptionMessages.CITY_NOT_FOUND,
                        ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<City> getAll() {
        List<City> cities = cityRepository.findAll();
        if (cities.isEmpty()) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.NO_CITIES_IN_BASE,
                    ExceptionCode.NOT_FOUND);
        }
        return cities;
    }

    @Transactional
    @Override
    public void delete(Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);
        if (city.isPresent()) {
            cityRepository.deleteById(cityId);
        } else {
            throw new ResourceNotFoundException(
                    ExceptionMessages.CITY_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public List<City> getByRegionId(Long regionId) {
        regionService.get(regionId);
        List<City> cities = findCitesByRegionId(regionId);

        if (cities.isEmpty()) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.NO_CITIES_IN_THIS_REGION_IN_BASE,
                    ExceptionCode.NOT_FOUND);
        }
        return cities;
    }

    boolean isCityAlreadyExistInDatabase(String cityName, Long regionId) {
        for (City city : isCityAlreadyExist(regionId)) {
            if (cityName.equals(city.getName())) {
                return true;
            }
        }
        return false;
    }

    private Long getRegionId(Long regionId) {
        return regionService.get(regionId).getId();
    }

    private List<City> isCityAlreadyExist(Long regionId) {
        ArrayList<City> cities = new ArrayList<>();

        if (regionId.equals(getRegionId(regionId))) {
            cities.addAll(findCitesByRegionId(regionId));
        }
        return cities;
    }

    private List<City> findCitesByRegionId(Long regionId) {
        List<City> regions = new ArrayList<>();

        for (City city : cityRepository.findAll()) {
            if (regionId.equals(getRegionIdInCity(city) )) {
                regions.add(city);
            }
        }
        return regions;
    }

    private Long getRegionIdInCity(City city) {
        return city.getRegion().getId();
    }
}
