package edu.unimagdalena.coworker.domain.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.unimagdalena.coworker.domain.entities.BookingItem;
import edu.unimagdalena.coworker.domain.enums.Cabin;

public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {
    public List<BookingItem> findByBooking_IdOrderBySegmentOrderAsc(Long bookingId);

    @Query("SELECT COALESCE(SUM(bi.price), 0) FROM BookingItem bi WHERE bi.booking.id = :bookingId")
    BigDecimal sumPricesByBookingId(Long bookingId);

    public Long countByFlight_IdAndCabin(Long flightId, Cabin cabin);
}
