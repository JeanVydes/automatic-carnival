package edu.unimagdalena.coworker.domain.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domain.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    public Page<Booking> findByPassengerEmailIgnoreCase(String email, Pageable pageable);

    @EntityGraph(attributePaths = {"items", "items.flight", "passenger"})
    public Optional<Booking> findWithItemsById(Long id);
}
