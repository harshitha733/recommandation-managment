package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.StateDto;
import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.repository.CountryRepository;
import com.epam.recommendation.management.application.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public StateDto convertToDTO(State state) {
        if (state == null) {
            return null;
        }

        StateDto stateDto = new StateDto();
        stateDto.setStateId(state.getStateId());
        stateDto.setStateName(state.getStateName());
        stateDto.setImageUrl(state.getImageUrl());
//        stateDto.setCountry(state.getCountry());

//        if (state.getCountry() != null) {
//            stateDto.getCountry().setCountryId(state.getCountry().getCountryId());
//        }

        return stateDto;
    }
    public Optional<List<StateDto>> getAllStatesByCountryId(Long countryId) {
        // Retrieve the list of states by country ID
        List<State> states = stateRepository.findByCountryCountryId(countryId);

        List<StateDto> stateDTOs = states.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return stateDTOs.isEmpty() ? Optional.empty() : Optional.of(stateDTOs);
    }

    public Page<State> getStatesByCountryWithPagination(Long countryId, Pageable pageable) {
        return stateRepository.findByCountry_CountryId(countryId, pageable);
    }

}
