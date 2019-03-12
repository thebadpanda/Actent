package com.softserve.actent.service.impl;

import com.softserve.actent.constant.ExceptionMessages;
import com.softserve.actent.exceptions.DuplicateValueException;
import com.softserve.actent.exceptions.ResourceNotFoundException;
import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.model.entity.Country;
import com.softserve.actent.repository.CountryRepository;
import com.softserve.actent.service.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional
    @Override
    public Country add(Country country) {
        if (isCountryAlreadyExistInDatabase(country.getName())) {
            throw new DuplicateValueException(
                    ExceptionMessages.COUNTRY_ALREADY_EXIST,
                    ExceptionCode.DUPLICATE_VALUE);
        } else {
            return countryRepository.save(country);
        }
    }

    @Transactional
    @Override
    public Country update(Country country, Long countryId) {
        if (isCountryAlreadyExistInDatabase(country.getName())) {
            throw new DuplicateValueException(
                    ExceptionMessages.COUNTRY_ALREADY_EXIST,
                    ExceptionCode.DUPLICATE_VALUE);
        } else {
            if (countryRepository.existsById(countryId)) {
                country.setId(countryId);
                return countryRepository.save(country);
            } else {
                throw new ResourceNotFoundException(
                        ExceptionMessages.COUNTRY_NOT_FOUND,
                        ExceptionCode.NOT_FOUND);
            }
        }
    }

    public Country get(Long countryId) {
        return countryRepository.findById(countryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ExceptionMessages.COUNTRY_NOT_FOUND,
                        ExceptionCode.NOT_FOUND));
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = countryRepository.findAll();

        if (countries.isEmpty()) {
            throw new ResourceNotFoundException(
                    ExceptionMessages.NO_COUNTRIES_IN_BASE,
                    ExceptionCode.NOT_FOUND);
        }
        return countries;
    }

    @Transactional
    @Override
    public void delete(Long countryId) {
        Optional<Country> country = countryRepository.findById(countryId);

        if (country.isPresent()) {
            countryRepository.deleteById(countryId);
        } else {
            throw new ResourceNotFoundException(
                    ExceptionMessages.COUNTRY_NOT_FOUND,
                    ExceptionCode.NOT_FOUND);
        }
    }

    boolean isCountryAlreadyExistInDatabase(String countryName) {
        for (Country country : getCountries()) {
            if (countryName.equals(country.getName())) {
                return true;
            }
        }
        return false;
    }

    private List<Country> getCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries;
    }
}
