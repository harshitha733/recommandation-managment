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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
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

        Country country = countryRepository.findByCountryName(destination.getState().getCountry().getCountryName())
//                .orElseGet(() -> countryRepository.save(destination.getState().getCountry()));
                .orElseThrow(()-> new EntityNotFoundException("no such country"));

        State state = stateRepository.findByStateNameAndCountry(destination.getState().getStateName(), country)
//                .orElseGet(() -> {
//                    destination.getState().setCountry(country);
//                    return stateRepository.save(destination.getState());
//                });
                .orElseThrow(() -> new EntityNotFoundException("State not found"));

        boolean destinationExists= destinationRepository.existsByDestinationNameAndStateStateNameAndStateCountryCountryName(
                destination.getDestinationName(), state.getStateName(), country.getCountryName()
        );

        if(destinationExists){
            throw new DestinationAlreadyExistsException("Destination already exists");
        }

        destination.setState(state);
        Destination updatedDestination =destinationRepository.save(destination);
        return new DestinationDetailsDTO(updatedDestination.getDestinationId(), updatedDestination.getDestinationName(), updatedDestination.getRating(), updatedDestination.getDescription(), updatedDestination.getImageUrl());
    }

    public Page<DestinationListDTO> getDestinationNamesByStateId(Long stateId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Destination> destinationPage = destinationRepository.findByStateStateId(stateId, pageable);

        if (destinationPage.isEmpty()) throw new EntityNotFoundException("No destinations found");

        List<DestinationListDTO> destinationList = destinationPage.stream()
                .map(destination -> new DestinationListDTO(destination.getDestinationId(),
                        destination.getDestinationName(), destination.getImageUrl()))
                .collect(Collectors.toList());

        return new PageImpl<>(destinationList, pageable, destinationPage.getTotalElements());
    }

    public DestinationDetailsDTO getDestinationInformation(Long destinationId){
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new EntityNotFoundException("Destination not found"));
        return new DestinationDetailsDTO(destination.getDestinationId(),destination.getDestinationName(),
                destination.getRating(),destination.getDescription(),destination.getImageUrl());
    }

    public DestinationDetailsDTO updateDestination(Long destinationId, Map<String,Object> destinationUpdateDetails) {
        Destination updatingDestination = destinationRepository.findById(destinationId).orElseThrow(() -> new EntityNotFoundException("No destination found with the id "+destinationId));
        destinationUpdateDetails.forEach((key, value) -> {
            try {
                switch (key) {
                    case "destinationName" -> updatingDestination.setDestinationName((String) value);
                    case "description" -> updatingDestination.setDescription((String) value);
                    case "imageUrl" -> updatingDestination.setImageUrl((String) value);
                    case "rating" ->updatingDestination.setRating((Double) value);
                }
            }catch (ClassCastException | IllegalArgumentException exception){
                throw new IllegalArgumentException("Not valid type of input format for "+ key);
            }

        });
        try {
            destinationRepository.save(updatingDestination);
            return new DestinationDetailsDTO(updatingDestination.getDestinationId(), updatingDestination.getDestinationName(), updatingDestination.getRating(), updatingDestination.getDescription(), updatingDestination.getImageUrl());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data integrity violation", e);
        }
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
