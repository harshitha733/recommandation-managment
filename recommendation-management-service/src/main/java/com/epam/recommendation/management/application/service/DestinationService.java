package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Destination;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface DestinationService {
    String deleteDestinationById(Long destinationId);
    Destination updateDestination(Long destinationId, Map<String,Object> destinationUpdateDetails);
    DestinationDetailsDTO getDestinationInformation(Long destinationId);
    Page<DestinationListDTO> getDestinationNamesByStateId(Long stateId, int page, int size);
    Destination createDestination(DestinationRequest request);
}
