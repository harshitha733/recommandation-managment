package com.epam.recommendation.management.application.repository;

import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {
    Optional<State> findByStateNameAndCountry(String stateName, Country country);

    List<State> findByCountryCountryId(Long countryId);
}
