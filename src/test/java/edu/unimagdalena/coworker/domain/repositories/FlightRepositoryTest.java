package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.Airline;
import edu.unimagdalena.coworker.domain.entities.Airport;
import edu.unimagdalena.coworker.domain.entities.Flight;
import edu.unimagdalena.coworker.domain.entities.Tag;
import edu.unimagdalena.coworker.domain.repositories.AirlineRepository;
import edu.unimagdalena.coworker.domain.repositories.AirportRepository;
import edu.unimagdalena.coworker.domain.repositories.FlightRepository;
import edu.unimagdalena.coworker.domain.repositories.TagRepository;

class FlightRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("Should find flights by airline name")
    void whenFindByAirlineName_thenReturnFlights() {
        Airline avianca = createAndSaveAirline("Avianca", "AV");
        Airport bogota = createAndSaveAirport("BOG", "El Dorado", "Bogotá");
        Airport madrid = createAndSaveAirport("MAD", "Barajas", "Madrid");

        Flight flight = new Flight();
        flight.setNumber("AV258");
        flight.setAirline(avianca);
        flight.setOrigin(bogota);
        flight.setDestination(madrid);
        flight.setDepartureAt(OffsetDateTime.parse("2025-09-22T10:00:00-05:00"));
        flight.setArrivalAt(OffsetDateTime.parse("2025-09-22T17:00:00+02:00"));
        flightRepository.save(flight);

        List<Flight> foundFlights = flightRepository.findByAirline_Name("Avianca");

        assertThat(foundFlights)
                .hasSize(1)
                .extracting(Flight::getNumber)
                .containsExactly("AV258");
    }

    @Test
    @DisplayName("Should find paged flights by origin, destination, and time window")
    void whenFindByOriginAndDestinationAndTimeWindow_thenReturnPagedFlights() {
        Airline avianca = createAndSaveAirline("Avianca", "AV");
        Airport bogota = createAndSaveAirport("BOG", "El Dorado", "Bogotá");
        Airport madrid = createAndSaveAirport("MAD", "Barajas", "Madrid");

        Flight flight = new Flight();
        flight.setNumber("AV258");
        flight.setAirline(avianca);
        flight.setOrigin(bogota);
        flight.setDestination(madrid);
        flight.setDepartureAt(OffsetDateTime.parse("2025-09-22T10:00:00-05:00"));
        flight.setArrivalAt(OffsetDateTime.parse("2025-09-22T17:00:00+02:00"));
        flightRepository.save(flight);

        OffsetDateTime from = OffsetDateTime.parse("2025-09-22T09:00:00-05:00");
        OffsetDateTime to = OffsetDateTime.parse("2025-09-22T11:00:00-05:00");

        var flights = flightRepository.findByOriginCodeAndDestinationCodeOptionalAndDepartureAtBetween("BOG", "MAD",
                from, to);

        assertThat(flights).hasSize(1);
        assertThat(flights.get(0).getNumber()).isEqualTo("AV258");
    }

    @Test
    @DisplayName("Should find flights that have all specified tags")
    void whenFindByAllTags_thenReturnMatchingFlights() {
        // Arrange
        Airline avianca = createAndSaveAirline("Avianca", "AV");
        Airport bogota = createAndSaveAirport("BOG", "El Dorado", "Bogotá");
        Airport madrid = createAndSaveAirport("MAD", "Barajas", "Madrid");

        Tag ecoTag = createAndSaveTag("eco");
        Tag promoTag = createAndSaveTag("promo");
        Tag otherTag = createAndSaveTag("other");

        Flight flightWithAllTags = new Flight();
        flightWithAllTags.setNumber("AV001");
        flightWithAllTags.setAirline(avianca);
        flightWithAllTags.setOrigin(bogota);
        flightWithAllTags.setDestination(madrid);
        flightWithAllTags.setDepartureAt(OffsetDateTime.now());
        flightWithAllTags.setArrivalAt(OffsetDateTime.now().plusHours(8));
        flightWithAllTags.setTags(Set.of(ecoTag, promoTag));
        flightRepository.save(flightWithAllTags);

        Flight flightWithSomeTags = new Flight();
        flightWithSomeTags.setNumber("AV002");
        flightWithSomeTags.setAirline(avianca);
        flightWithSomeTags.setOrigin(bogota);
        flightWithSomeTags.setDestination(madrid);
        flightWithSomeTags.setDepartureAt(OffsetDateTime.now());
        flightWithSomeTags.setArrivalAt(OffsetDateTime.now().plusHours(8));
        flightWithSomeTags.setTags(Set.of(ecoTag, otherTag));
        flightRepository.save(flightWithSomeTags);

        List<String> requiredTags = List.of("eco", "promo");
        long requiredCount = requiredTags.size();

        List<Flight> flights = flightRepository.findByAllTags(requiredTags, requiredCount);

        assertThat(flights)
                .hasSize(1)
                .extracting(Flight::getNumber)
                .containsExactly("AV001");
    }

    private Airline createAndSaveAirline(String name, String code) {
        Airline airline = new Airline();
        airline.setName(name);
        airline.setCode(code);
        return airlineRepository.save(airline);
    }

    private Airport createAndSaveAirport(String code, String name, String city) {
        Airport airport = new Airport();
        airport.setCode(code);
        airport.setName(name);
        airport.setCity(city);
        return airportRepository.save(airport);
    }

    private Tag createAndSaveTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }
}