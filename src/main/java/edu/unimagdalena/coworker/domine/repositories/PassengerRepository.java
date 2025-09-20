package edu.unimagdalena.coworker.domine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domine.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
