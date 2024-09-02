package com.epam.recommendation.managment.application;

import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.exception.DestinationAlreadyExistsException;
import com.epam.recommendation.management.application.repository.CountryRepository;
import com.epam.recommendation.management.application.repository.DestinationRepository;
import com.epam.recommendation.management.application.repository.StateRepository;
import com.epam.recommendation.management.application.service.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DestinationServiceTest {
    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private StateRepository stateRepository;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private DestinationService destinationService;

    private DestinationRequest destinationRequest;
    private State state;
    private Country country;
    private Destination destination;

    @BeforeEach
    void setUp() {
        // Initialize test data
        country = new Country();
        country.setCountryName("India");

        state = new State();
        state.setStateName("Karnataka");
        state.setCountry(country);

        destinationRequest = new DestinationRequest();
        destinationRequest.setDestinationName("Bangalore");
        destinationRequest.setState(state);

        destination = new Destination();
        destination.setDestinationName("Bangalore");
        destination.setState(state);
    }

    @Test
    void createDestination_Success() {
        when(countryRepository.findByCountryName(anyString())).thenReturn(java.util.Optional.of(country));
        when(stateRepository.findByStateNameAndCountry(anyString(), any(Country.class))).thenReturn(java.util.Optional.of(state));
        when(destinationRepository.existsByDestinationNameAndStateStateNameAndStateCountryCountryName(
                anyString(), anyString(), anyString())).thenReturn(false);
        when(destinationRepository.save(any(Destination.class))).thenReturn(destination);

        Destination createdDestination = destinationService.createDestination(destinationRequest);

        assertNotNull(createdDestination);
        assertEquals("Bangalore", createdDestination.getDestinationName());
        verify(destinationRepository, times(1)).save(any(Destination.class));
    }

    @Test
    void createDestination_DestinationAlreadyExists() {
        when(countryRepository.findByCountryName(anyString())).thenReturn(java.util.Optional.of(country));
        when(stateRepository.findByStateNameAndCountry(anyString(), any(Country.class))).thenReturn(java.util.Optional.of(state));
        when(destinationRepository.existsByDestinationNameAndStateStateNameAndStateCountryCountryName(
                anyString(), anyString(), anyString())).thenReturn(true);

        assertThrows(DestinationAlreadyExistsException.class, () -> destinationService.createDestination(destinationRequest));

        verify(destinationRepository, never()).save(any(Destination.class));
    }

}
