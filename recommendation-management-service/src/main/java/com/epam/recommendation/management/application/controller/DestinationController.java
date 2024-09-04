package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.DestinationDetailsDTO;
import com.epam.recommendation.management.application.dto.DestinationListDTO;
import com.epam.recommendation.management.application.dto.DestinationRequest;
import com.epam.recommendation.management.application.entity.Destination;
import com.epam.recommendation.management.application.response.ApiResponse;
import com.epam.recommendation.management.application.service.DestinationService;
import jakarta.validation.Valid;
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


    @GetMapping("/{stateId}")
    public ResponseEntity<ApiResponse<Page<DestinationListDTO>>> getDestinationNamesByStateId(
            @PathVariable("stateId") Long stateId,
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "8") int size) {

        Page<DestinationListDTO> destinationPage = destinationService.getDestinationNamesByStateId(stateId, page, size);
        return new ResponseEntity<>(ApiResponse.<Page<DestinationListDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("successfully retrieved the destinations of state")
                .data(destinationPage)
                .build(),HttpStatus.OK);
    }

    @GetMapping("/details/{destinationId}")
    public ResponseEntity<ApiResponse<DestinationDetailsDTO>> getDestination(@PathVariable("destinationId") Long destinationId){
        DestinationDetailsDTO destination=destinationService.getDestinationInformation(destinationId);

        return new ResponseEntity<>(ApiResponse.<DestinationDetailsDTO>builder()
                .status(HttpStatus.OK.value())
                .message("successfully retreived the destination details")
                .data(destination)
                .build(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DestinationDetailsDTO>> createDestination(@RequestBody @Valid DestinationRequest request) {
        DestinationDetailsDTO savedDestination = destinationService.createDestination(request);
        ApiResponse<DestinationDetailsDTO> response = ApiResponse.<DestinationDetailsDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Destination created successfully.")
                .data(savedDestination)
                .build();
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PatchMapping("{destinationId}")
    public ResponseEntity<ApiResponse<DestinationDetailsDTO>> updateDestination(@PathVariable("destinationId") Long destinationId,@RequestBody Map<String,Object> destinationUpdateDetails){
        DestinationDetailsDTO destination=destinationService.updateDestination(destinationId, destinationUpdateDetails);
        return new ResponseEntity<>(ApiResponse.<DestinationDetailsDTO>builder()
                .status(HttpStatus.OK.value())
                .message("updated the destination details")
                .data(destination)
                .build(),HttpStatus.OK);
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<ApiResponse<String>> deleteDestination(@PathVariable("destinationId") Long destinationId) {
        String response=destinationService.deleteDestinationById(destinationId);
        return new ResponseEntity<>(ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("deleted the destination successfully")
                .data(response)
                .build(),HttpStatus.OK);
    }

}