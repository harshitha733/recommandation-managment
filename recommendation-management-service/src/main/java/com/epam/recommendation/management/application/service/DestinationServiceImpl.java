package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.exception.DestinationAlreadyExistsException;
import com.epam.recommendation.management.application.exception.EntityNotFoundException;
import com.epam.recommendation.management.application.repository.DestinationRepository;
import com.epam.recommendation.management.application.repository.StateRepository;
import com.epam.recommendation.management.application.repository.CountryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService{


    private final DestinationRepository destinationRepository;


    private final StateRepository stateRepository;


    private final CountryRepository countryRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository, StateRepository stateRepository, CountryRepository countryRepository) {
        this.destinationRepository = destinationRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
    }

    public Destination convertToEntity(DestinationRequest request){
        Destination destination =new Destination();
        destination.setDestinationName(request.getDestinationName());
        destination.setRating(request.getRating());
        destination.setDescription(request.getDescription());
        destination.setImageUrl(request.getImageUrl());
        destination.setState(request.getState());
        return destination;
    }

    public DestinationDetailsDTO createDestination(DestinationRequest request) {
        Destination destination=convertToEntity(request);

        Country country = countryRepository.findByCountryName(destination.getState().getCountry().getCountryName()).orElseThrow(()-> {throw new EntityNotFoundException("No country found");});

        State state = stateRepository.findByStateNameAndCountry(destination.getState().getStateName(), country).orElseThrow(() -> new EntityNotFoundException("State not found"));

        boolean destinationExists= destinationRepository.existsByDestinationNameAndStateStateNameAndStateCountryCountryName(destination.getDestinationName(), state.getStateName(), country.getCountryName()
        );

        if(destinationExists){
            throw new DestinationAlreadyExistsException("Destination already exists");
        }
        destination.setState(state);
        Destination updatedDestination =destinationRepository.save(destination);
        return new DestinationDetailsDTO(updatedDestination);
    }

    public Page<DestinationListDTO> getDestinationNamesByStateId(Long stateId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Destination> destinationPage = destinationRepository.findByStateStateId(stateId, pageable);

        if (destinationPage.isEmpty()) throw new EntityNotFoundException("No destinations found");

        List<DestinationListDTO> destinationList = destinationPage.stream()
                .map(DestinationListDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(destinationList, pageable, destinationPage.getTotalElements());
    }


    public DestinationDetailsDTO getDestinationInformation(Long destinationId){
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new EntityNotFoundException("Destination not found"));
        return new DestinationDetailsDTO(destination);
    }
    private Destination updateDetailsMapper(Destination updatingDestination, String destinationUpdateDetails) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        DestinationRequest request = mapper.readValue(destinationUpdateDetails, DestinationRequest.class);

        if (request.getDestinationName() != null) {
            updatingDestination.setDestinationName(request.getDestinationName());
        }
        if (request.getDescription() != null) {
            updatingDestination.setDescription(request.getDescription());
        }
        if (request.getImageUrl() != null) {
            updatingDestination.setImageUrl(request.getImageUrl());
        }
        if (request.getRating() != null) {
            updatingDestination.setRating(request.getRating());
        }

        return updatingDestination;
    }

    public DestinationDetailsDTO updateDestination(Long destinationId, String destinationUpdateDetails) throws JsonProcessingException {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> new EntityNotFoundException("No destination found with the id "+destinationId));
        Destination updatingDestination=updateDetailsMapper(destination, destinationUpdateDetails);
        return new DestinationDetailsDTO(destinationRepository.save(updatingDestination));
    }

    public String deleteDestinationById(Long destinationId) {
        if (destinationRepository.existsById(destinationId)) {
            destinationRepository.deleteById(destinationId);
            return "Destination with ID " + destinationId + " has been successfully deleted.";
        } else {
            throw new EntityNotFoundException("Destination not found with id: " + destinationId);
        }
    }
}