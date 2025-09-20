package edu.unimagdalena.coworker.domine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domine.entities.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    
}
