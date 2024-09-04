package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;

public interface DestinationService {
    String deleteDestinationById(Long destinationId);
    DestinationDetailsDTO updateDestination(Long destinationId, String destinationUpdateDetails) throws JsonProcessingException;
    DestinationDetailsDTO getDestinationInformation(Long destinationId);
    Page<DestinationListDTO> getDestinationNamesByStateId(Long stateId, int page, int size);
    DestinationDetailsDTO createDestination(DestinationRequest request);
}