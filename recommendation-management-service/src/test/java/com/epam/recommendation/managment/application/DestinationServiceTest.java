package com.epam.recommendation.managment.application;

import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.exception.DestinationAlreadyExistsException;
import com.epam.recommendation.management.application.exception.EntityNotFoundException;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
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
    private Destination originalDestination;

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

        originalDestination = new Destination();
        originalDestination.setDestinationId(1L);
        originalDestination.setDestinationName("Bangalore");
        originalDestination.setDescription("great place");
        originalDestination.setState(state);
        originalDestination.setRating(4.5);
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

    @Test
    public void testUpdateDestination() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "New Name");
        updates.put("description", "New Description");

        Destination updatedDestination = new Destination();
        given(destinationRepository.findById(originalDestination.getDestinationId())).willReturn(Optional.of(originalDestination));
        given(destinationRepository.save(any(Destination.class))).willReturn(originalDestination);

        Destination result = destinationService.updateDestination(originalDestination.getDestinationId(), updates);

        assertThat(result.getDestinationName()).isEqualTo("New Name");
        assertThat(result.getDescription()).isEqualTo("New Description");

        verify(destinationRepository).save(any(Destination.class));
    }

    @Test
    public void testUpdateDestinationNotFound() {
        Long destinationId = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "New Name");

        given(destinationRepository.findById(destinationId)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            destinationService.updateDestination(destinationId, updates);
        });
    }
    @Test
    public void testUpdateDestinationInvalidInputType() {
        Long destinationId = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("rating", "Five");  // Invalid type, should be Double

        given(destinationRepository.findById(destinationId)).willReturn(Optional.of(originalDestination));

        assertThrows(IllegalArgumentException.class, () -> {
            destinationService.updateDestination(destinationId, updates);
        });
    }

    @Test
    public void testUpdateDestinationDataIntegrityViolation() {
        Long destinationId = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "New Name");

        given(destinationRepository.findById(destinationId)).willReturn(Optional.of(originalDestination));
        given(destinationRepository.save(any(Destination.class))).willThrow(DataIntegrityViolationException.class);

        assertThrows(ResponseStatusException.class, () -> {
            destinationService.updateDestination(destinationId, updates);
        });
    }

}
