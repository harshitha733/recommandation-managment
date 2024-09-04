package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.CountryDto;
import com.epam.recommendation.management.application.response.ApiResponse;
import com.epam.recommendation.management.application.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ApiResponse<Page<CountryDto>> getCountries(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "8") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CountryDto> countryList = countryService.getAllCountries(pageable);

        return ApiResponse.<Page<CountryDto>>builder()
                .status(HttpStatus.OK.value())
                .message("Countries retrieved successfully.")
                .data(countryList)
                .build();
    }

}
