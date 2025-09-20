package edu.unimagdalena.coworker.domine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unimagdalena.coworker.domine.entities.BookingItem;

public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {

}
