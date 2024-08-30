package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.exception.EntityNotFoundException;
import com.epam.recommendation.management.application.exception.ResourceNotFoundException;
import com.epam.recommendation.management.application.repository.DestinationRepository;
import com.epam.recommendation.management.application.repository.StateRepository;
import com.epam.recommendation.management.application.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public Destination convertToEntity(DestinationRequest request){
        Destination destination =new Destination();
        destination.setDestinationName(request.getDestinationName());
        destination.setRating(request.getRating());
        destination.setDescription(request.getDescription());
        destination.setImageUrl(request.getImageUrl());
        destination.setState(request.getState());
        return destination;
    }

    public Destination createDestination(DestinationRequest request) {
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

        destination.setState(state);
        return destinationRepository.save(destination);
    }

    public List<DestinationListDTO> getDestinationNamesByStateId(Long stateId){
        List<Destination> destinationList=destinationRepository.findByStateStateId(stateId).get();
        if (destinationList.isEmpty()) throw new ResourceNotFoundException("State not found");

        return destinationList.stream()
                .map(destination -> new DestinationListDTO(destination.getDestinationId(),
                        destination.getDestinationName(),destination.getImageUrl()))
                .collect(Collectors.toList());
    }

    public DestinationDetailsDTO getDestinationInformation(Long destinationId){
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));
        return new DestinationDetailsDTO(destination.getDestinationId(),destination.getDestinationName(),
                destination.getRating(),destination.getDescription(),destination.getImageUrl());
    }


    public Destination updateDestination(Long destinationId, Map<String,Object> destinationUpdateDetails) {
        Destination updatingDestination = destinationRepository.findById(destinationId).orElseThrow(() -> new EntityNotFoundException("No destination found with the id "+destinationId));
        destinationUpdateDetails.forEach((key, value) -> {
            try {
                switch (key) {
                    case "name" -> updatingDestination.setDestinationName((String) value);
                    case "description" -> updatingDestination.setDescription((String) value);
                    case "imageUrl" -> updatingDestination.setImageUrl((String) value);
                    case "rating" ->updatingDestination.setRating((Double) value);
                }
            }catch (ClassCastException | IllegalArgumentException exception){
                throw new IllegalArgumentException("Not valid type of input format for "+ key);
            }

        });
        try {
            return destinationRepository.save(updatingDestination);
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
