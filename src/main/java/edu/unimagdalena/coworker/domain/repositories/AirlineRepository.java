package edu.unimagdalena.coworker.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domain.entities.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    public Optional<Airline> findByCode(String code);
}
