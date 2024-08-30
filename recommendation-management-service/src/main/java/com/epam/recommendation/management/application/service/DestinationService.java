package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.exception.EntityNotFoundException;
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

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public Destination createDestination(Destination destination) {

        Country country = countryRepository.findByCountryName(destination.getState().getCountry().getCountryName())
                .orElseGet(() -> countryRepository.save(destination.getState().getCountry()));


        State state = stateRepository.findByStateNameAndCountry(destination.getState().getStateName(), country)
                .orElseGet(() -> {
                    destination.getState().setCountry(country);
                    destination.getState().setImageUrl(destination.getState().getImageUrl());
                    return stateRepository.save(destination.getState());
                });

        destination.setState(state);
        return destinationRepository.save(destination);
    }

    public List<Destination> getDestinationsByStateId(Long stateId){
        return destinationRepository.findByStateStateId(stateId);
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public List<Destination> getAllDestinationsByStateId(Long stateId) {
        return destinationRepository.findByStateStateId(stateId);
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

    public void deleteDestinationById(Long destinationId) {
        destinationRepository.deleteById(destinationId);
        System.out.println("deleted");
    }
}
