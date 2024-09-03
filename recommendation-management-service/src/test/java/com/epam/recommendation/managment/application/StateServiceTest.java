package com.epam.recommendation.managment.application;

import com.epam.recommendation.management.application.dto.StateDto;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.repository.StateRepository;
import com.epam.recommendation.management.application.repository.CountryRepository;
import com.epam.recommendation.management.application.service.StateServiceImpl;
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

public class StateServiceTest {

    @Mock
    private StateRepository stateRepository;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private StateServiceImpl stateService;

    public StateServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStatesByCountryWithPagination() {
        // Prepare test data
        State state1 = new State();
        state1.setStateId(1L);
        state1.setStateName("State1");

        State state2 = new State();
        state2.setStateId(2L);
        state2.setStateName("State2");

        Pageable pageable = PageRequest.of(0, 10);
        Page<State> page = new PageImpl<>(Arrays.asList(state1, state2), pageable, 2);

        // Mock repository method
        when(stateRepository.findByCountry_CountryId(1L, pageable)).thenReturn(page);

        // Call service method
        Page<StateDto> result = stateService.getStatesByCountryWithPagination(1L, pageable);

        // Verify results
        assertEquals(2, result.getTotalElements());
        assertEquals("State1", result.getContent().get(0).getStateName());
        assertEquals("State2", result.getContent().get(1).getStateName());
    }
    @Test
    public void testGetStatesByCountryWithPagination_EmptyPage() {
        // Prepare test data
        Pageable pageable = PageRequest.of(0, 10);
        Page<State> page = new PageImpl<>(Collections.emptyList(), pageable, 0);

        // Mock repository method
        when(stateRepository.findByCountry_CountryId(1L, pageable)).thenReturn(page);

        // Call service method
        Page<StateDto> result = stateService.getStatesByCountryWithPagination(1L, pageable);

        // Verify results
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());
    }
    @Test
    public void testGetStatesByCountryWithPagination_InvalidCountryId() {
        // Prepare test data
        Pageable pageable = PageRequest.of(0, 10);
        Page<State> page = new PageImpl<>(Collections.emptyList(), pageable, 0);

        // Mock repository method
        when(stateRepository.findByCountry_CountryId(999L, pageable)).thenReturn(page);

        // Call service method
        Page<StateDto> result = stateService.getStatesByCountryWithPagination(999L, pageable);

        // Verify results
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getContent().isEmpty());

    }

}
