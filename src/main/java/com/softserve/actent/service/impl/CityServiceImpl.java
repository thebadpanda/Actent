package com.softserve.actent.service.impl;

import com.softserve.actent.model.entity.City;
import com.softserve.actent.repository.CityRepository;
import com.softserve.actent.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @Transactional
    @Override
    public City add(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City get(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        return optionalCity.orElse(null);
    }
    @Transactional
    @Override
    public City update(City city, Long id) {
        if (cityRepository.findById(id).isPresent()) {
            city.setId(id);
            return cityRepository.save(city);
        } else {
            // TODO: else throw exception
            return null;
        }
    }
    @Transactional
    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> getByRegionId(Long regionId) {
        return cityRepository.getAllByRegionId(regionId);
    }
}
