package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.dto.CountryDto;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<CountryDto>> getCountries(){
        List<CountryDto> countryList=countryService.getAllCountries();
        return ResponseEntity.ok(countryList);
    }

//    @PostMapping
//    public ResponseEntity<Country> add(@RequestBody Country country){
//       return ResponseEntity.ok(countryService.addCountry(country));
//    }




}
