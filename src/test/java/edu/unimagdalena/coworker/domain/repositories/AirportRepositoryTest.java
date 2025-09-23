package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.Airport;

class AirportRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private AirportRepository airportRepository;

    @Test
    @DisplayName("Debe recuperar un aeropuerto por su código IATA")
    void shouldFindByCode() {
        Airport elDorado = new Airport();
        elDorado.setCode("BOG");
        elDorado.setName("El Dorado");
        elDorado.setCity("Bogotá");
        airportRepository.save(elDorado);

        Optional<Airport> foundAirport = airportRepository.findByCode("BOG");

        assertThat(foundAirport).isPresent();
        assertThat(foundAirport.get().getName()).isEqualTo("El Dorado");
        assertThat(foundAirport.get().getCity()).isEqualTo("Bogotá");
    }

    @Test
    @DisplayName("No debe encontrar un aeropuerto si el código no existe")
    void shouldNotFindByCodeWhenCodeDoesNotExist() {
        Optional<Airport> foundAirport = airportRepository.findByCode("XXX");
        assertThat(foundAirport).isEmpty();
    }
}