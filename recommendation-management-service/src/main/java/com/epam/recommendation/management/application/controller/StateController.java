package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.CountryDto;
import com.epam.recommendation.management.application.dto.StateDto;
import com.epam.recommendation.management.application.response.ApiResponse;
import com.epam.recommendation.management.application.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@CrossOrigin
@RestController
@RequestMapping("v1/")
public class StateController {

    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("countries/{countryId}/states")
    public ResponseEntity<ApiResponse<Page<StateDto>>> getStatesByCountryWithPagination(
            @PathVariable(name = "countryId") Long countryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<StateDto> statesPage = stateService.getStatesByCountryWithPagination(countryId, pageable);

        return new ResponseEntity<>(ApiResponse.<Page<StateDto>>builder()
                .status(HttpStatus.OK.value())
                .message("States retrieved successfully.")
                .data(statesPage)
                .build(),HttpStatus.OK);

    }
}
