package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Page<CountryDto> getAllCountries(Pageable pageable);
}