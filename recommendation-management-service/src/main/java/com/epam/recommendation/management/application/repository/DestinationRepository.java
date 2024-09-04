package com.epam.recommendation.management.application.repository;

import com.epam.recommendation.management.application.entity.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {

    Page<Destination> findByStateStateId(Long stateId, Pageable pageable);

    boolean existsByDestinationNameAndStateStateNameAndStateCountryCountryName(String stateName, String countryName, String destinationName);

}
