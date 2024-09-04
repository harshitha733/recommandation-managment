package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface DestinationService {
    String deleteDestinationById(Long destinationId);
    DestinationDetailsDTO updateDestination(Long destinationId, Map<String,Object> destinationUpdateDetails);
    DestinationDetailsDTO getDestinationInformation(Long destinationId);
    Page<DestinationListDTO> getDestinationNamesByStateId(Long stateId, int page, int size);
    DestinationDetailsDTO createDestination(DestinationRequest request);
}
