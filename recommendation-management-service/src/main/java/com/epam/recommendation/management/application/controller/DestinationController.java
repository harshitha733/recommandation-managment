package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.response.ApiResponse;
import com.epam.recommendation.management.application.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;
    @PostMapping
    public ResponseEntity<ApiResponse<Destination>> createDestination(@RequestBody DestinationRequest request) {
        Destination savedDestination = destinationService.createDestination(request);
        ApiResponse<Destination> response = ApiResponse.<Destination>builder()
                .status(HttpStatus.CREATED.value())
                .message("Destination created successfully.")
                .data(savedDestination)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<List<DestinationListDTO>> getDestinations(@PathVariable("stateId") Long stateId){
        List<DestinationListDTO> destinations=destinationService.getDestinationNamesByStateId(stateId);
        return  ResponseEntity.ok(destinations);
    }

    @GetMapping("/details/{destinationId}")
    public ResponseEntity<DestinationDetailsDTO> getDestination(@PathVariable("destinationId") Long destinationId){
        DestinationDetailsDTO destination=destinationService.getDestinationInformation(destinationId);
        return  ResponseEntity.ok(destination);
    }

    @PatchMapping("{destinationId}")
    public ResponseEntity<Destination> updateDestination(@PathVariable("destinationId") Long destinationId,@RequestBody Map<String,Object> destinationUpdateDetails){
        Destination destination=destinationService.updateDestination(destinationId, destinationUpdateDetails);
        return ResponseEntity.ok(destination);
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable("destinationId") Long destinationId) {
        destinationService.deleteDestinationById(destinationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
