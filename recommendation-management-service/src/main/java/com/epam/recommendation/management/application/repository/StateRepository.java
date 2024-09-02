package com.epam.recommendation.management.application.repository;

import com.epam.recommendation.management.application.entity.Country;
import com.epam.recommendation.management.application.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {
    Optional<State> findByStateNameAndCountry(String stateName, Country country);

    List<State> findByCountryCountryId(Long countryId);
    Page<State> findByCountry_CountryId(Long countryId, Pageable pageable);

}
