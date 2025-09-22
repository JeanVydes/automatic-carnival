package edu.unimagdalena.coworker.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domain.entities.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    public Optional<Airport> findByCode(String code);
}
