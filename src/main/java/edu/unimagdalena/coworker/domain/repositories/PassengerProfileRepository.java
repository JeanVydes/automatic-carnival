package edu.unimagdalena.coworker.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domain.entities.PassengerProfile;

public interface PassengerProfileRepository extends JpaRepository<PassengerProfile, Long> {

}
