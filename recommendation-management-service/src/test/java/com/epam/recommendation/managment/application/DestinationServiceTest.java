package com.epam.recommendation.managment.application;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.exception.DestinationAlreadyExistsException;
import com.epam.recommendation.management.application.exception.EntityNotFoundException;
import com.epam.recommendation.management.application.exception.ResourceNotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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
        originalDestination.setImageUrl("https://example.com/bangalore.jpg");
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


    @Test
    public void testGetDestinationNamesByStateId_ReturnsDestinationList() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Destination> destinations = Arrays.asList(originalDestination);
        Page<Destination> destinationPage = new PageImpl<>(destinations, pageable, destinations.size());

        when(destinationRepository.findByStateStateId(state.getStateId(), pageable))
                .thenReturn(destinationPage);

        Page<DestinationListDTO> result = destinationService.getDestinationNamesByStateId(state.getStateId(), 0, 10);

        assertEquals(1, result.getTotalElements());
        assertEquals("Bangalore", result.getContent().get(0).getDestinationName());
    }

    @Test
    public void testGetDestinationNamesByStateId_InvalidPageNumber_ThrowsException() {
        Long stateId = 1L;
        int invalidPage = -1;
        int size = 2;

        assertThrows(IllegalArgumentException.class, () ->
                destinationService.getDestinationNamesByStateId(stateId, invalidPage, size)
        );
    }

    @Test
    void testGetDestinationNamesByStateId_EmptyResults() {
        Pageable pageable = PageRequest.of(0, 10);
        when(destinationRepository.findByStateStateId(state.getStateId(), pageable)).thenReturn(Page.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            destinationService.getDestinationNamesByStateId(state.getStateId(), 0, 10);
        });

        verify(destinationRepository, times(1)).findByStateStateId(state.getStateId(), pageable);
    }

    @Test
    void testGetDestinationInformation_Found() {
        when(destinationRepository.findById(originalDestination.getDestinationId()))
                .thenReturn(java.util.Optional.of(originalDestination));

        DestinationDetailsDTO result = destinationService.getDestinationInformation(originalDestination.getDestinationId());

        assertEquals(originalDestination.getDestinationName(), result.getDestinationName());
        assertEquals(originalDestination.getDescription(), result.getDescription());
        assertEquals(originalDestination.getRating(), result.getRating());
        verify(destinationRepository, times(1)).findById(originalDestination.getDestinationId());
    }

    @Test
    void testGetDestinationInformation_NotFound() {
        when(destinationRepository.findById(originalDestination.getDestinationId()))
                .thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            destinationService.getDestinationInformation(originalDestination.getDestinationId());
        });

        verify(destinationRepository, times(1)).findById(originalDestination.getDestinationId());
    }

    @Test
    void deleteDestinationById_Success() {
        // Given
        Long destinationId = 1L;

        when(destinationRepository.existsById(destinationId)).thenReturn(true);

        // When
        String result = destinationService.deleteDestinationById(destinationId);

        // Then
        assertEquals("Destination with ID " + destinationId + " has been successfully deleted.", result);
        verify(destinationRepository, times(1)).deleteById(destinationId);
    }

    @Test
    void deleteDestinationById_NotFound() {
        // Given
        Long destinationId = 1L;

        when(destinationRepository.existsById(destinationId)).thenReturn(false);

        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            destinationService.deleteDestinationById(destinationId);
        });

        assertEquals("Destination not found with id: " + destinationId, exception.getMessage());
        verify(destinationRepository, never()).deleteById(destinationId);
    }




}
