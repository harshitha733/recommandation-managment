package com.epam.recommendation.management.application.repository;

import com.epam.recommendation.management.application.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Optional<Country> findByCountryName(String countryName);

    List<Country> findAll();

    Optional<Country> getCountryByCountryName(String countryN);
}
