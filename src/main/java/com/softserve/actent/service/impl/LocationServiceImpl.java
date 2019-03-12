package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.DuplicateValueException;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Location;
import com.softserve.actent.repository.LocationRepository;
import com.softserve.actent.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final CityServiceImpl cityService;

    @Transactional
    @Override
    public Location add(Location location) {
        Location newLocation = new Location();
        newLocation.setAddress(location.getAddress());
        newLocation.setCity(location.getCity());

        if (isLocationAlreadyExistInDatabase(newLocation.getAddress(), getCityIdInLocation(newLocation))) {
            throw new DuplicateValueException(
                    ExceptionMessages.LOCATION_ALREADY_EXIST,
                    ExceptionCode.DUPLICATE_VALUE);
        } else {
            return locationRepository.save(newLocation);
        }
    }

    @Transactional
    @Override
    public Location update(Location location, Long id) {
        if (locationRepository.existsById(id)) {
            Location dbLocation = locationRepository.getOne(id);

            if (isLocationAlreadyExistInDatabase(location.getAddress(), getCityIdInLocation(dbLocation))
                    && !location.getAddress().equals(dbLocation.getAddress())) {
                throw new DuplicateValueException(
                        ExceptionMessages.LOCATION_ALREADY_EXIST,
                        ExceptionCode.DUPLICATE_VALUE);
            } else {
                dbLocation.setAddress(location.getAddress());
                return locationRepository.save(dbLocation);
            }

        } else {
            throw new ResourceNotFoundException(
                    ExceptionMessages.LOCATION_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public Location get(Long locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ExceptionMessages.LOCATION_NOT_FOUND,
                        ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<Location> getAll() {
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty()) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.NO_LOCATIONS_IN_BASE,
                    ExceptionCode.NOT_FOUND);
        }
        return locations;
    }

    @Transactional
    @Override
    public void delete(Long locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if ((location.isPresent())) {
            locationRepository.deleteById(locationId);
        } else {
            throw new ResourceNotFoundException(
                    ExceptionMessages.LOCATION_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public List<Location> getByCityId(Long cityId) {
        cityService.get(cityId);
        List<Location> locations = findLocationByCityId(cityId);

        if (locations.isEmpty()) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.NO_LOCATIONS_IN_THIS_CITY_IN_BASE,
                    ExceptionCode.NOT_FOUND);
        }
        return locations;
    }

    boolean isLocationAlreadyExistInDatabase(String address, Long cityId) {
        for (Location location : isLocationAlreadyExist(cityId)) {
            if (address.equals(location.getAddress())) {
                return true;
            }
        }
        return false;
    }

    private Long getCityId(Long cityId) {
        return cityService.get(cityId).getId();
    }

    private List<Location> isLocationAlreadyExist(Long cityId) {
        ArrayList<Location> cities = new ArrayList<>();
        if (cityId.equals(getCityId(cityId))) {
            cities.addAll(findLocationByCityId(cityId));
        }
        return cities;
    }

    private List<Location> findLocationByCityId(Long cityId) {
        List<Location> locations = new ArrayList<>();
        for (Location location : locationRepository.findAll()) {
            if (cityId.equals(getCityIdInLocation(location))) {
                locations.add(location);
            }
        }
        return locations;
    }

    private Long getCityIdInLocation(Location location) {
        return location.getCity().getId();
    }

}
