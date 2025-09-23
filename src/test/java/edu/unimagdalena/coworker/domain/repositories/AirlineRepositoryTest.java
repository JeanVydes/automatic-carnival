package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.Airline;


class AirlineRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private AirlineRepository airlineRepository;

    @Test
    @DisplayName("Debe recuperar una aerolínea por su código IATA")
    void shouldFindByCode() {
        Airline avianca = new Airline();
        avianca.setName("Avianca");
        avianca.setCode("AV");
        airlineRepository.save(avianca);

        Optional<Airline> foundAirline = airlineRepository.findByCode("AV");

        assertThat(foundAirline).isPresent();
        assertThat(foundAirline.get().getName()).isEqualTo("Avianca");
        assertThat(foundAirline.get().getCode()).isEqualTo("AV");
    }

    @Test
    @DisplayName("No debe encontrar una aerolínea si el código no existe")
    void shouldNotFindByCodeWhenCodeDoesNotExist() {
        Optional<Airline> foundAirline = airlineRepository.findByCode("XX");
        assertThat(foundAirline).isNotPresent();
    }
}