package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.dto.StateDto;
import com.epam.recommendation.management.application.entity.State;
import com.epam.recommendation.management.application.repository.StateRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class StateServiceImpl implements StateService{

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public StateDto convertToDTO(State state) {
        if (state == null) return null;
        StateDto stateDto = new StateDto();
        stateDto.setStateId(state.getStateId());
        stateDto.setStateName(state.getStateName());
        stateDto.setImageUrl(state.getImageUrl());
        return stateDto;
    }

    public Page<StateDto> getStatesByCountryWithPagination(Long countryId, Pageable pageable) {
        return stateRepository.findByCountry_CountryId(countryId, pageable).map(this::convertToDTO);
    }

}
