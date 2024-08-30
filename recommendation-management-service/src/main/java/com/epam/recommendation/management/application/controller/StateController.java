package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping
    public ResponseEntity<State> add(@RequestBody State state){
        return ResponseEntity.ok(stateService.addState(state));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<State>> getStatesByCountryId(@PathVariable("id") Long countryId){
        List<State> stateList=stateService.getAllStatesByCountryId(countryId);
        return  ResponseEntity.ok(stateList);
    }
}
