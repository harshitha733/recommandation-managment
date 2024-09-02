package com.epam.recommendation.managment.application;

import com.epam.recommendation.management.application.dto.CountryDto;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.repository.CountryRepository;
import com.epam.recommendation.management.application.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCountries_Success() {
        // Prepare data
        Country country1 = new Country(1L, "India", "http...india.jpg", null);
        Country country2 = new Country(2L, "USA", "http...usa.jpg", null);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Country> countryPage = new PageImpl<>(Arrays.asList(country1, country2), pageable, 2);

        // Mock behavior
        when(countryRepository.findAll(pageable)).thenReturn(countryPage);

        // Execute
        Page<CountryDto> result = countryService.getAllCountries(pageable);

        // Verify
        verify(countryRepository, times(1)).findAll(pageable);
        assertEquals(2, result.getTotalElements());
        assertEquals("India", result.getContent().get(0).getCountryName());
        assertEquals("USA", result.getContent().get(1).getCountryName());
    }

    @Test
    void testGetAllCountries_Empty() {
        // Prepare data
        Pageable pageable = PageRequest.of(0, 10);
        Page<Country> emptyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);

        // Mock behavior
        when(countryRepository.findAll(pageable)).thenReturn(emptyPage);

        // Execute
        Page<CountryDto> result = countryService.getAllCountries(pageable);

        // Verify
        verify(countryRepository, times(1)).findAll(pageable);
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
    }

    @Test
    void testGetAllCountries_PaginationBoundaries() {
        // Initialize data
        Country country1 = new Country(1L, "India", "http..india.jpg", null);
        Country country2 = new Country(2L, "USA", "http...usa.jpg", null);
        Country country3 = new Country(3L, "Canada", "http...canada.jpg", null);

        // Page requests for testing boundary conditions
        Pageable firstPage = PageRequest.of(0, 2);
        Pageable secondPage = PageRequest.of(1, 2);
        Pageable outOfBoundPage = PageRequest.of(2, 2); // No data should be here

        // Mocking repository for each page request
        when(countryRepository.findAll(firstPage)).thenReturn(new PageImpl<>(Arrays.asList(country1, country2), firstPage, 3));
        when(countryRepository.findAll(secondPage)).thenReturn(new PageImpl<>(Collections.singletonList(country3), secondPage, 3));
        when(countryRepository.findAll(outOfBoundPage)).thenReturn(new PageImpl<>(Collections.emptyList(), outOfBoundPage, 3));

        // Perform service calls
        Page<CountryDto> firstPageResults = countryService.getAllCountries(firstPage);
        Page<CountryDto> secondPageResults = countryService.getAllCountries(secondPage);
        Page<CountryDto> outOfBoundPageResults = countryService.getAllCountries(outOfBoundPage);


        assertEquals("India", firstPageResults.getContent().get(0).getCountryName());
        assertEquals("USA", firstPageResults.getContent().get(1).getCountryName());


        assertEquals("Canada", secondPageResults.getContent().get(0).getCountryName());

        assertTrue(outOfBoundPageResults.isEmpty());
    }
}