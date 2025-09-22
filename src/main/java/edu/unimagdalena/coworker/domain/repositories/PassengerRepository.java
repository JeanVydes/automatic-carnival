package edu.unimagdalena.coworker.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.unimagdalena.coworker.domain.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    public Optional<Passenger> findByEmailIgnoreCase(String email);

    @Query("SELECT p FROM Passenger p LEFT JOIN FETCH p.profile WHERE p.email = :email")
    public Optional<Passenger> findByEmailIgnoreCaseWithProfile(String email);
}
