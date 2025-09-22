package edu.unimagdalena.coworker;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import edu.unimagdalena.coworker.domain.entities.Airline;
import edu.unimagdalena.coworker.domain.entities.Airport;
import edu.unimagdalena.coworker.domain.entities.Booking;
import edu.unimagdalena.coworker.domain.entities.BookingItem;
import edu.unimagdalena.coworker.domain.entities.Flight;
import edu.unimagdalena.coworker.domain.entities.Passenger;
import edu.unimagdalena.coworker.domain.entities.SeatInventory;
import edu.unimagdalena.coworker.domain.entities.Tag;
import edu.unimagdalena.coworker.domain.repositories.AirlineRepository;
import edu.unimagdalena.coworker.domain.repositories.AirportRepository;
import edu.unimagdalena.coworker.domain.repositories.BookingItemRepository;
import edu.unimagdalena.coworker.domain.repositories.BookingRepository;
import edu.unimagdalena.coworker.domain.repositories.FlightRepository;
import edu.unimagdalena.coworker.domain.repositories.PassengerRepository;
import edu.unimagdalena.coworker.domain.repositories.SeatInventoryRepository;
import edu.unimagdalena.coworker.domain.repositories.TagRepository;
import edu.unimagdalena.coworker.domain.enums.Cabin;

@Testcontainers
@DataJpaTest
class CoworkerApplicationTests {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private BookingItemRepository bookingItemRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private SeatInventoryRepository seatInventoryRepository;
    @Autowired
    private TagRepository tagRepository;
    // Note: PassengerProfileRepository is not needed for the tests specified in the document

    private Airline avianca;
    private Airline iberia;
    private Airport bogota;
    private Airport madrid;
    private Tag ecoTag;
    private Tag promoTag;
    private Passenger passenger;
    private Booking booking;
    private Flight flight1;
    private Flight flight2;
    private SeatInventory seatInventory;

    @BeforeEach
    void setUp() {
        // --- Setup for FlightRepository tests ---
        avianca = new Airline();
        avianca.setName("Avianca");
        avianca.setCode("AV");
        airlineRepository.save(avianca);

        iberia = new Airline();
        iberia.setName("Iberia");
        iberia.setCode("IB");
        airlineRepository.save(iberia);

        bogota = new Airport();
        bogota.setCode("BOG");
        bogota.setName("El Dorado");
        bogota.setCity("Bogotá");
        airportRepository.save(bogota);

        madrid = new Airport();
        madrid.setCode("MAD");
        madrid.setName("Adolfo Suárez Madrid-Barajas");
        madrid.setCity("Madrid");
        airportRepository.save(madrid);

        ecoTag = new Tag();
        ecoTag.setName("eco");
        tagRepository.save(ecoTag);

        promoTag = new Tag();
        promoTag.setName("promo");
        tagRepository.save(promoTag);
        
        flight1 = new Flight();
        flight1.setNumber("AV258");
        flight1.setDepartureAt(OffsetDateTime.parse("2025-09-22T10:00:00-05:00"));
        flight1.setArrivalAt(OffsetDateTime.parse("2025-09-22T17:00:00+02:00"));
        flight1.setAirline(avianca);
        flight1.setOrigin(bogota);
        flight1.setDestination(madrid);
        flight1.setTags(Set.of(ecoTag, promoTag));
        flightRepository.save(flight1);

        flight2 = new Flight();
        flight2.setNumber("IB6586");
        flight2.setDepartureAt(OffsetDateTime.parse("2025-09-23T12:00:00-05:00"));
        flight2.setArrivalAt(OffsetDateTime.parse("2025-09-23T19:00:00+02:00"));
        flight2.setAirline(iberia);
        flight2.setOrigin(bogota);
        flight2.setDestination(madrid);
        flightRepository.save(flight2);

        // --- Setup for SeatInventoryRepository tests ---
        seatInventory = new SeatInventory();
        seatInventory.setFlight(flight1);
        seatInventory.setCabin(Cabin.ECONOMY);
        seatInventory.setTotalSeats(200);
        seatInventory.setAvailableSeats(150);
        seatInventoryRepository.save(seatInventory);

        // --- Setup for BookingRepository & BookingItemRepository tests ---
        passenger = new Passenger();
        passenger.setFullName("Juan Perez");
        passenger.setEmail("juan.perez@email.com");
        passengerRepository.save(passenger);

        booking = new Booking();
        booking.setPassenger(passenger);
        bookingRepository.save(booking);

        BookingItem item1 = new BookingItem();
        item1.setBooking(booking);
        item1.setFlight(flight1);
        item1.setSegmentOrder(1);
        item1.setPrice(new BigDecimal("150.50"));
        item1.setCabin(Cabin.ECONOMY);
        bookingItemRepository.save(item1);

        BookingItem item2 = new BookingItem();
        item2.setBooking(booking);
        item2.setFlight(flight2);
        item2.setSegmentOrder(2);
        item2.setPrice(new BigDecimal("200.00"));
        item2.setCabin(Cabin.ECONOMY);
        bookingItemRepository.save(item2);
    }
    
    //--------------------------------------------------------------------------------------------------
    // FlightRepository Tests
    //--------------------------------------------------------------------------------------------------

    @Test
    void whenFindByAirlineName_thenReturnFlights() {
        List<Flight> flights = flightRepository.findByAirline_Name("Avianca");
        assertThat(flights).hasSize(1);
        assertThat(flights.get(0).getNumber()).isEqualTo("AV258");
    }

    @Test
    void whenFindByOriginAndDestinationAndTimeWindow_thenReturnPagedFlights() {
        OffsetDateTime from = OffsetDateTime.parse("2025-09-22T09:00:00-05:00");
        OffsetDateTime to = OffsetDateTime.parse("2025-09-22T11:00:00-05:00");
        Pageable pageable = PageRequest.of(0, 10);

        var flightsPage = flightRepository.findByOrigin_CodeAndDestination_CodeAndDepartureAtBetween("BOG", "MAD", from, to, pageable);
        assertThat(flightsPage.getContent()).hasSize(1);
        assertThat(flightsPage.getContent().get(0).getNumber()).isEqualTo("AV258");
    }

    @Test
    void whenFindByOptionalOriginAndDestinationAndWindow_thenReturnFlightsWithLoadedAssociations() {
        OffsetDateTime from = OffsetDateTime.parse("2025-09-22T09:00:00-05:00");
        OffsetDateTime to = OffsetDateTime.parse("2025-09-23T13:00:00-05:00");
        
        List<Flight> flights = flightRepository.findByOriginCodeAndDestinationCodeOptionalAndDepartureAtBetween("BOG", "MAD", from, to);
        
        assertThat(flights).hasSize(2);
        assertThat(flights.stream().map(f -> f.getAirline().getName()).collect(Collectors.toList())).containsExactlyInAnyOrder("Avianca", "Iberia");
    }

    @Test
    void whenFindByAllTags_thenReturnMatchingFlights() {
        List<String> tags = List.of("eco", "promo");
        long required = tags.size();
        List<Flight> flights = flightRepository.findByAllTags(tags, required);
        assertThat(flights).hasSize(1);
        assertThat(flights.get(0).getNumber()).isEqualTo("AV258");
    }
    
    //--------------------------------------------------------------------------------------------------
    // SeatInventoryRepository Tests
    //--------------------------------------------------------------------------------------------------

    @Test
    void whenFindByFlightIdAndCabin_thenReturnSeatInventory() {
        var found = seatInventoryRepository.findByFlightIdAndCabin(flight1.getId(), Cabin.ECONOMY);
        assertThat(found).isNotNull();
        assertThat(found.getAvailableSeats()).isEqualTo(150);
    }

    @Test
    void whenCheckAvailableSeats_thenReturnTrueForSufficientSeats() {
        boolean isSufficient = seatInventoryRepository.existsFlightAvailableSeats(flight1.getId(), Cabin.ECONOMY, 10);
        assertThat(isSufficient).isTrue();
    }

    @Test
    void whenCheckAvailableSeats_thenReturnFalseForInsufficientSeats() {
        boolean isSufficient = seatInventoryRepository.existsFlightAvailableSeats(flight1.getId(), Cabin.ECONOMY, 200);
        assertThat(isSufficient).isFalse();
    }
    
    //--------------------------------------------------------------------------------------------------
    // BookingRepository Tests
    //--------------------------------------------------------------------------------------------------
    
    @Test
    void whenFindByPassengerEmailIgnoreCaseOrderByCreatedAtDesc_thenReturnPagedBookings() {
        Pageable pageable = PageRequest.of(0, 10);
        var bookings = bookingRepository.findByPassengerEmailIgnoreCase("JUAN.PEREZ@EMAIL.COM", pageable);
        assertThat(bookings.getContent()).hasSize(1); // Only one booking created in this setup
        assertThat(bookings.getContent().get(0).getPassenger().getEmail()).isEqualTo("juan.perez@email.com");
    }

    @Test
    void whenFindByIdWithDetails_thenReturnBookingWithAssociations() {
        var found = bookingRepository.findById(booking.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getPassenger()).isNotNull();
    }
    
    //--------------------------------------------------------------------------------------------------
    // BookingItemRepository Tests
    //--------------------------------------------------------------------------------------------------
    
    @Test
    void whenFindByBookingIdOrderBySegmentOrderAsc_thenReturnOrderedItems() {
        List<BookingItem> items = bookingItemRepository.findByBooking_IdOrderBySegmentOrderAsc(booking.getId());
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getSegmentOrder()).isEqualTo(1);
        assertThat(items.get(1).getSegmentOrder()).isEqualTo(2);
    }

    @Test
    void whenSumPricesByBookingId_thenReturnTotalSum() {
        BigDecimal total = bookingItemRepository.sumPricesByBookingId(booking.getId());
        assertThat(total).isEqualByComparingTo("350.50");
    }

    @Test
    void whenCountByFlightIdAndCabin_thenReturnCount() {
        long count = bookingItemRepository.countByFlight_IdAndCabin(flight1.getId(), Cabin.ECONOMY);
        assertThat(count).isEqualTo(1);
    }
}