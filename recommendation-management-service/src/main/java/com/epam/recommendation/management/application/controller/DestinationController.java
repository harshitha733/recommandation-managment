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
    public ApiResponse<Destination> createDestination(@RequestBody DestinationRequest request) {
        Destination savedDestination = destinationService.createDestination(request);
        return ApiResponse.<Destination>builder()
                .status(HttpStatus.CREATED.value())
                .message("Destination created successfully.")
                .data(savedDestination)
                .build();
    }

    @GetMapping("/{stateId}")
    public ApiResponse<Page<DestinationListDTO>> getDestinationNamesByStateId(
            @PathVariable("stateId") Long stateId,
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "8") int size) {

        Page<DestinationListDTO> destinationPage = destinationService.getDestinationNamesByStateId(stateId, page, size);
        return ApiResponse.<Page<DestinationListDTO>>builder()
                .status(HttpStatus.OK.value())
                .message("successfully retreived the destinations of state")
                .data(destinationPage)
                .build();
    }

    @GetMapping("/details/{destinationId}")
    public ApiResponse<DestinationDetailsDTO> getDestination(@PathVariable("destinationId") Long destinationId){
        DestinationDetailsDTO destination=destinationService.getDestinationInformation(destinationId);

        return ApiResponse.<DestinationDetailsDTO>builder()
                .status(HttpStatus.OK.value())
                .message("successfully retreived the destination details")
                .data(destination)
                .build();
    }

    @PatchMapping("{destinationId}")
    public ApiResponse<DestinationDetailsDTO> updateDestination(@PathVariable("destinationId") Long destinationId,@RequestBody Map<String,Object> destinationUpdateDetails){
        DestinationDetailsDTO destination=destinationService.updateDestination(destinationId, destinationUpdateDetails);
        return ApiResponse.<DestinationDetailsDTO>builder()
                .status(HttpStatus.OK.value())
                .message("updated the destination details")
                .data(destination)
                .build();
    }

    @DeleteMapping("/{destinationId}")
    public ApiResponse<String> deleteDestination(@PathVariable("destinationId") Long destinationId) {
        String response=destinationService.deleteDestinationById(destinationId);
        return ApiResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("deleted the destination successfully")
                .data(response)
                .build();
    }

}
