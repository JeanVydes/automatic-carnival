package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.Airline;
import edu.unimagdalena.coworker.domain.entities.Airport;
import edu.unimagdalena.coworker.domain.entities.Booking;
import edu.unimagdalena.coworker.domain.entities.BookingItem;
import edu.unimagdalena.coworker.domain.entities.Flight;
import edu.unimagdalena.coworker.domain.entities.Passenger;
import edu.unimagdalena.coworker.domain.enums.Cabin;

class BookingItemRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private BookingItemRepository bookingItemRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;

    private Booking booking;
    private Flight flight;

    @BeforeEach
    void setUp() {
        Passenger passenger = createAndSavePassenger("Juan Perez", "juan.perez@test.com");
        Airline airline = createAndSaveAirline("Avianca", "AV");
        Airport origin = createAndSaveAirport("BOG", "El Dorado", "Bogotá");
        Airport destination = createAndSaveAirport("MAD", "Barajas", "Madrid");
        flight = createAndSaveFlight("AV001", airline, origin, destination);

        booking = new Booking();
        booking.setPassenger(passenger);
        booking.setCreatedAt(OffsetDateTime.now());
        bookingRepository.save(booking);
    }

    @Test
    @DisplayName("Debe listar los segmentos de una reserva en orden")
    void shouldListBookingItemsInSegmentOrder() {
        createAndSaveBookingItem(booking, flight, 2, "200.00", Cabin.ECONOMY);
        createAndSaveBookingItem(booking, flight, 1, "150.50", Cabin.BUSINESS);

        List<BookingItem> items = bookingItemRepository.findByBooking_IdOrderBySegmentOrderAsc(booking.getId());

        assertThat(items).hasSize(2);
        assertThat(items.get(0).getSegmentOrder()).isEqualTo(1);
        assertThat(items.get(1).getSegmentOrder()).isEqualTo(2);
    }

    @Test
    @DisplayName("Debe calcular el total de la reserva sumando los precios")
    void shouldCalculateTotalBookingPrice() {
        createAndSaveBookingItem(booking, flight, 1, "150.50", Cabin.ECONOMY);
        createAndSaveBookingItem(booking, flight, 2, "200.00", Cabin.ECONOMY);

        BigDecimal total = bookingItemRepository.sumPricesByBookingId(booking.getId());

        assertThat(total).isEqualByComparingTo("350.50");
    }

    @Test
    @DisplayName("Debe retornar 0 si la reserva no tiene items")
    void shouldReturnZeroForBookingWithNoItems() {
        BigDecimal total = bookingItemRepository.sumPricesByBookingId(booking.getId());
        assertThat(total).isEqualByComparingTo("0");
    }

    @Test
    @DisplayName("Debe contar los asientos reservados para un vuelo y cabina")
    void shouldCountReservedSeatsForFlightAndCabin() {
        createAndSaveBookingItem(booking, flight, 1, "150.50", Cabin.ECONOMY);
        long count = bookingItemRepository.countByFlight_IdAndCabin(flight.getId(), Cabin.ECONOMY);
        assertThat(count).isEqualTo(1);
    }

    private Passenger createAndSavePassenger(String name, String email) {
        Passenger p = new Passenger();
        p.setFullName(name);
        p.setEmail(email);
        return passengerRepository.save(p);
    }

    private Airline createAndSaveAirline(String name, String code) {
        Airline a = new Airline();
        a.setName(name);
        a.setCode(code);
        return airlineRepository.save(a);
    }

    private Airport createAndSaveAirport(String code, String name, String city) {
        Airport a = new Airport();
        a.setCode(code);
        a.setName(name);
        a.setCity(city);
        return airportRepository.save(a);
    }

    private Flight createAndSaveFlight(String number, Airline airline, Airport origin, Airport dest) {
        Flight f = new Flight();
        f.setNumber(number);
        f.setAirline(airline);
        f.setOrigin(origin);
        f.setDestination(dest);
        f.setDepartureAt(OffsetDateTime.now());
        f.setArrivalAt(OffsetDateTime.now().plusHours(1));
        return flightRepository.save(f);
    }

    private void createAndSaveBookingItem(Booking booking, Flight flight, int order, String price, Cabin cabin) {
        BookingItem item = new BookingItem();
        item.setBooking(booking);
        item.setFlight(flight);
        item.setSegmentOrder(order);
        item.setPrice(new BigDecimal(price));
        item.setCabin(cabin);
        bookingItemRepository.save(item);
    }
}