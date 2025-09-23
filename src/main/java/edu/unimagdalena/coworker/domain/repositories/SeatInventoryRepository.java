package edu.unimagdalena.coworker.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.unimagdalena.coworker.domain.entities.SeatInventory;
import edu.unimagdalena.coworker.domain.enums.Cabin;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory, Long> {
    public Optional<SeatInventory> findByFlightIdAndCabin(Long flightId, Cabin cabin);

    @Query("SELECT (s.availableSeats >= :quantity) FROM SeatInventory s WHERE s.flight.id = :flightId AND s.cabin = :cabin")
    public boolean existsFlightAvailableSeats(Long flightId, Cabin cabin, int quantity);
    
}
