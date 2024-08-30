package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.CountryDto;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.repository.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country addCountry(Country country){
        return countryRepository.save(country);
    }

    public List<CountryDto> getAllCountries() {

        return countryRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private CountryDto convertToDTO(Country country) {
        return new CountryDto(country.getCountryId(),country.getCountryName(),country.getImageUrl());
    }

}
