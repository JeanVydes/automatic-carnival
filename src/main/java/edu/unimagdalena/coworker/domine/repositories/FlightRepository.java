package edu.unimagdalena.coworker.domine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domine.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
