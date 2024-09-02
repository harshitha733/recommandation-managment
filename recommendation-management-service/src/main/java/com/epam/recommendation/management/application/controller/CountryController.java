package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.CountryDto;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping
    public ResponseEntity<Page<CountryDto>> getCountries(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "9") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CountryDto> countryList = countryService.getAllCountries(pageable);
        return ResponseEntity.ok(countryList);
    }

}
