package edu.unimagdalena.coworker;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.domain.entities.Airline;
import edu.unimagdalena.coworker.domain.repositories.AirlineRepository;

public class FlightRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private AirlineRepository airlineRepository;

    @Test
    @DisplayName("Should save and retrieve an airline")
    void shouldSaveAndRetrieveAirline() {
        Airline airline = new Airline();
        airline.setName("Avianca");
        airline.setCode("AV");

        Airline savedAirline = airlineRepository.save(airline);

        assertThat(savedAirline.getId()).isNotNull();
        assertThat(savedAirline.getName()).isEqualTo("Avianca");
    }
}