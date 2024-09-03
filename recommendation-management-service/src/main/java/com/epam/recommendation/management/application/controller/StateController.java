package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.service.StateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@CrossOrigin
@RestController
@RequestMapping("v1/states")
public class StateController {

    @Autowired
    private StateServiceImpl stateService;

//    @PostMapping
//    public ResponseEntity<State> add(@RequestBody State state){
//        return ResponseEntity.ok(stateService.addState(state));
//    }

//    @GetMapping("/{countryId}")
//    public ResponseEntity<List<StateDto>> getStatesByCountryId(@PathVariable("countryId") Long countryId){
//        List<StateDto> stateList = stateService.getAllStatesByCountryId(countryId)
//                .orElseThrow(() -> new ResourceNotFoundException("States not found for country ID: " + countryId));
//
//        return ResponseEntity.ok(stateList);
//    }

    @GetMapping("{countryId}")
    public Page<State> getStatesByCountryWithPagination(
            @PathVariable(name="countryId") Long countryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10")  int size) {
        Pageable pageable = PageRequest.of(page, size);
        return stateService.getStatesByCountryWithPagination(countryId, pageable);
    }

}
