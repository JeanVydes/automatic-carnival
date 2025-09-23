package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.Booking;
import edu.unimagdalena.coworker.domain.entities.Passenger;

class BookingRepositoryTest extends AbstractIntegrationTest {
    
    @Autowired private BookingRepository bookingRepository;
    @Autowired private PassengerRepository passengerRepository;

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        passenger.setFullName("Ana Rodriguez");
        passenger.setEmail("ana.rodriguez@test.com");
        passengerRepository.save(passenger);
    }

    @Test
    @DisplayName("Debe paginar las reservas de un pasajero por email, ignorando caso")
    void shouldPageBookingsByPassengerEmail() {
        Booking oldBooking = new Booking();
        oldBooking.setPassenger(passenger);
        oldBooking.setCreatedAt(OffsetDateTime.now().minusDays(1));
        bookingRepository.save(oldBooking);
        
        Booking newBooking = new Booking();
        newBooking.setPassenger(passenger);
        newBooking.setCreatedAt(OffsetDateTime.now());
        bookingRepository.save(newBooking);

        Pageable pageable = PageRequest.of(0, 5);

        Page<Booking> bookings = bookingRepository.findByPassengerEmailIgnoreCase("ANA.RODRIGUEZ@TEST.COM", pageable);

        assertThat(bookings).hasSize(2);
        assertThat(bookings.getContent().get(0).getId()).isEqualTo(newBooking.getId());
    }

    @Test
    @DisplayName("Debe traer una reserva por ID con sus asociaciones precargadas")
    void shouldFindByIdWithDetails() {
        Booking booking = new Booking();
        booking.setPassenger(passenger);
        booking.setCreatedAt(OffsetDateTime.now());
        bookingRepository.save(booking);
        
        Optional<Booking> foundBooking = bookingRepository.findWithItemsById(booking.getId());

        assertThat(foundBooking).isPresent();
        assertThat(foundBooking.get().getPassenger()).isNotNull();
        assertThat(foundBooking.get().getPassenger().getFullName()).isEqualTo("Ana Rodriguez");
    }
}