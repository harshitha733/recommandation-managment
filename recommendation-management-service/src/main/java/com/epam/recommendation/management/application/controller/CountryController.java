package com.epam.recommendation.management.application.controller;

import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

//    @PostMapping
//    public ResponseEntity<Country> add(@RequestBody Country country){
//       return ResponseEntity.ok(countryService.addCountry(country));
//    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries(){
        List<Country> countryList=countryService.getAllCountries();
        return ResponseEntity.ok(countryList);
    }


}
