package com.epam.recommendation.management.application.repository;

import com.epam.recommendation.management.application.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {

    List<Destination> findByStateStateId(Long stateId);

}
