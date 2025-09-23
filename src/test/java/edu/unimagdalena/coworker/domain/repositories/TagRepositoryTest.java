package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.Airline;
import edu.unimagdalena.coworker.domain.entities.Airport;
import edu.unimagdalena.coworker.domain.entities.Flight;
import edu.unimagdalena.coworker.domain.entities.SeatInventory;
import edu.unimagdalena.coworker.domain.enums.Cabin;

class TagRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private SeatInventoryRepository seatInventoryRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;

    private Flight flight;

    @BeforeEach
    void setUp() {
        Airline airline = new Airline();
        airline.setName("LATAM");
        airline.setCode("LA");
        airlineRepository.save(airline);

        Airport origin = new Airport();
        origin.setCode("BOG");
        origin.setName("El Dorado");
        origin.setCity("Bogotá");
        airportRepository.save(origin);

        Airport destination = new Airport();
        destination.setCode("MIA");
        destination.setName("Miami International");
        destination.setCity("Miami");
        airportRepository.save(destination);

        flight = new Flight();
        flight.setNumber("LA500");
        flight.setAirline(airline);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureAt(OffsetDateTime.now());
        flight.setArrivalAt(OffsetDateTime.now().plusHours(4));
        flightRepository.save(flight);
    }

    @Test
    @DisplayName("Debe obtener el inventario de asientos para un vuelo y cabina específicos")
    void shouldFindByFlightIdAndCabin() {
        SeatInventory inventory = new SeatInventory();
        inventory.setFlight(flight);
        inventory.setCabin(Cabin.ECONOMY);
        inventory.setTotalSeats(200);
        inventory.setAvailableSeats(150);
        seatInventoryRepository.save(inventory);

        Optional<SeatInventory> foundInventory = seatInventoryRepository.findByFlightIdAndCabin(flight.getId(),
                Cabin.ECONOMY);
        Optional<SeatInventory> notFoundInventory = seatInventoryRepository.findByFlightIdAndCabin(flight.getId(),
                Cabin.BUSINESS);

        assertThat(foundInventory).isPresent();
        assertThat(foundInventory.get().getAvailableSeats()).isEqualTo(150);
        assertThat(notFoundInventory).isNotPresent();
    }

    @Test
    @DisplayName("Debe verificar si hay suficientes asientos disponibles")
    void shouldVerifyIfAvailableSeatsAreSufficient() {
        SeatInventory inventory = new SeatInventory();
        inventory.setFlight(flight);
        inventory.setCabin(Cabin.BUSINESS);
        inventory.setTotalSeats(50);
        inventory.setAvailableSeats(20);
        seatInventoryRepository.save(inventory);

        boolean hasEnoughSeats = seatInventoryRepository.existsFlightAvailableSeats(flight.getId(), Cabin.BUSINESS, 10);
        assertThat(hasEnoughSeats).isTrue();

        boolean hasNotEnoughSeats = seatInventoryRepository.existsFlightAvailableSeats(flight.getId(), Cabin.BUSINESS,
                25);
        assertThat(hasNotEnoughSeats).isFalse();

        boolean hasExactSeats = seatInventoryRepository.existsFlightAvailableSeats(flight.getId(), Cabin.BUSINESS, 20);
        assertThat(hasExactSeats).isTrue();
    }
}