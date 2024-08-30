package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.entity.Destination;
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
    public ResponseEntity<?> createDestination(@RequestBody Destination destination) {
        try {
            Destination savedDestination = destinationService.createDestination(destination);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "status", "success",
                            "message", "Destination created successfully.",
                            "destinationId", savedDestination.getDestinationId()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "status", "error",
                            "message", "Failed to create destination. " + e.getMessage()
                    ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Destination>> getDestinationsByStateId(@PathVariable("id") Long stateId){
        List<Destination> destinations = destinationService.getAllDestinationsByStateId(stateId);
        return ResponseEntity.ok(destinations); // Return the list of destinations with a 200 OK status

    }

    @PatchMapping("{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable("id") Long destinationId,@RequestBody Map<String,Object> destinationUpdateDetails){
        Destination destination=destinationService.updateDestination(destinationId, destinationUpdateDetails);
        return ResponseEntity.ok(destination);
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<Void> deleteDestination(@PathVariable("destinationId") Long destinationId) {
        destinationService.deleteDestinationById(destinationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
