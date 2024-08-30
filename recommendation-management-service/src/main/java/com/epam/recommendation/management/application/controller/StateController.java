package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.StateDto;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.exception.ResourceNotFoundException;
import com.epam.recommendation.management.application.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<State> add(@RequestBody State state){
        return ResponseEntity.ok(stateService.addState(state));
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<List<StateDto>> getStatesByCountryId(@PathVariable("countryId") Long countryId){
        List<StateDto> stateList = stateService.getAllStatesByCountryId(countryId)
                .orElseThrow(() -> new ResourceNotFoundException("States not found for country ID: " + countryId));

        // Return the list with a 200 OK status
        return ResponseEntity.ok(stateList);
    }

}
