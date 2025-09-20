package edu.unimagdalena.coworker.domine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domine.entities.PassengerProfile;

public interface PassengerProfileRepository extends JpaRepository<PassengerProfile, Long> {

}
