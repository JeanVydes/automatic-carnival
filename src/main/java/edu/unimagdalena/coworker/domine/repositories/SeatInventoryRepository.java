package edu.unimagdalena.coworker.domine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domine.entities.SeatInventory;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory, Long> {
    
}
