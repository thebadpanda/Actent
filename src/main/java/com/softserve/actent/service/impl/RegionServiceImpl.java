package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.DuplicateValueException;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Region;
import com.softserve.actent.repository.RegionRepository;
import com.softserve.actent.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final CountryServiceImpl countryService;

    @Transactional
    @Override
    public Region add(Region region) {
        Region newRegion = new Region();
        newRegion.setName(region.getName());
        newRegion.setCountry(region.getCountry());

        if (isRegionAlreadyExistInDatabase(newRegion.getName(), getCountryIdInRegion(newRegion))) {
            throw new DuplicateValueException(
                    ExceptionMessages.REGION_ALREADY_EXIST,
                    ExceptionCode.DUPLICATE_VALUE);
        } else {
            return regionRepository.save(newRegion);
        }
    }

    @Transactional
    @Override
    public Region update(Region region, Long regionId) {
        if (regionRepository.existsById(regionId)) {
            Region dbRegion = regionRepository.getOne(regionId);

            if (isRegionAlreadyExistInDatabase(region.getName(), getCountryIdInRegion(dbRegion))
                    && !region.getName().equals(dbRegion.getName())) {
                throw new DuplicateValueException(
                        ExceptionMessages.REGION_ALREADY_EXIST,
                        ExceptionCode.DUPLICATE_VALUE);
            }
            dbRegion.setName(region.getName());
            return regionRepository.save(dbRegion);

        } else {
            throw new ResourceNotFoundException(
                    ExceptionMessages.REGION_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public Region get(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ExceptionMessages.REGION_NOT_FOUND,
                        ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = regionRepository.findAll();
        if (regions.isEmpty()) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.NO_REGIONS_IN_BASE,
                    ExceptionCode.NOT_FOUND);
        }
        return regions;
    }

    @Transactional
    @Override
    public void delete(Long regionId) {
        Optional<Region> region = regionRepository.findById(regionId);
        if (region.isPresent()) {
            regionRepository.deleteById(regionId);
        } else {
            throw new ResourceNotFoundException(
                    ExceptionMessages.REGION_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    @Override
    public List<Region> getByCountryId(Long countryId) {
        countryService.isCountryAlreadyExistInDatabase(getCountryName(countryId));
        List<Region> regions = findRegionsByCountryId(countryId);

        if (regions.isEmpty()) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.NO_REGIONS_IN_THIS_COUNTRY_IN_BASE,
                    ExceptionCode.NOT_FOUND);
        }
        return regions;

    }

    private boolean isRegionAlreadyExistInDatabase(String regionName, Long countryId) {
        for (Region region : isRegionAlreadyExist(countryId)) {
            if (regionName.equals(region.getName())) {
                return true;
            }
        }
        return false;
    }

    private String getCountryName(Long id) {
        return countryService.get(id).getName();
    }

    private List<Region> isRegionAlreadyExist(Long countryId) {
        ArrayList<Region> regions = new ArrayList<>();
        if (countryService.isCountryAlreadyExistInDatabase(getCountryName(countryId))) {
            regions.addAll(findRegionsByCountryId(countryId));
        }
        return regions;
    }

    private List<Region> findRegionsByCountryId(Long countryId) {
        List<Region> regions = new ArrayList<>();
        for (Region region : regionRepository.findAll()) {
            if (countryId.equals(region.getCountry().getId())) {
                regions.add(region);
            }
        }
        return regions;
    }

    private Long getCountryIdInRegion(Region region) {
        return region.getCountry().getId();
    }
}