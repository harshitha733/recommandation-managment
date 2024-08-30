package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.repository.CountryRepository;
import com.epam.recommendation.management.application.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public State addState(State state) {
        Country country = countryRepository.findByCountryName(state.getCountry().getCountryName())
                .orElseGet(() -> countryRepository.save(state.getCountry()));
        state.setCountry(country);

        return stateRepository.save(state);
    }

    public List<State> getAllStatesByCountryId(Long countryId){
        return stateRepository.findByCountryCountryId(countryId);
    }
}
