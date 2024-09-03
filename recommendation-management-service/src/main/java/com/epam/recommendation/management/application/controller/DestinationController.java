package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.response.ApiResponse;
import com.epam.recommendation.management.application.service.DestinationService;
import com.epam.recommendation.management.application.service.DestinationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("v1/destinations")
public class DestinationController {


    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

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
    public ResponseEntity<Page<DestinationListDTO>> getDestinationNamesByStateId(
            @PathVariable("stateId") Long stateId,
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "9") int size) {

        Page<DestinationListDTO> destinationPage = destinationService.getDestinationNamesByStateId(stateId, page, size);
        return ResponseEntity.ok(destinationPage);
    }

    @GetMapping("/details/{destinationId}")
    public ResponseEntity<DestinationDetailsDTO> getDestination(@PathVariable("destinationId") Long destinationId){
        DestinationDetailsDTO destination=destinationService.getDestinationInformation(destinationId);
        return  ResponseEntity.ok(destination);
    }

    @PatchMapping("{destinationId}")
    public ResponseEntity<DestinationDetailsDTO> updateDestination(@PathVariable("destinationId") Long destinationId,@RequestBody Map<String,Object> destinationUpdateDetails){
        DestinationDetailsDTO destination=destinationService.updateDestination(destinationId, destinationUpdateDetails);
        return ResponseEntity.ok(destination);
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<String> deleteDestination(@PathVariable("destinationId") Long destinationId) {
        String response=destinationService.deleteDestinationById(destinationId);
        return ResponseEntity.ok(response);
    }


}
