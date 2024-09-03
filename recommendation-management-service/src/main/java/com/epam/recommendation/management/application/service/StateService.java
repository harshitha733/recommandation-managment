package com.epam.recommendation.management.application.service;

import com.epam.recommendation.management.application.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StateService {
   Page<State> getStatesByCountryWithPagination(Long countryId, Pageable pageable);
}
