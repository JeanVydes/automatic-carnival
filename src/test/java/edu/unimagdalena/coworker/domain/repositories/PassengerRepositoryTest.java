package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.Passenger;
import edu.unimagdalena.coworker.domain.entities.PassengerProfile;

class PassengerRepositoryTest extends AbstractIntegrationTest {

    @Autowired private PassengerRepository passengerRepository;
    @Autowired private PassengerProfileRepository profileRepository;
    
    @Test
    @DisplayName("Debe buscar un pasajero por email, ignorando mayúsculas y minúsculas")
    void shouldFindByEmailIgnoreCase() {
        Passenger passenger = new Passenger();
        passenger.setFullName("Carlos Gomez");
        passenger.setEmail("carlos.gomez@test.com");
        passengerRepository.save(passenger);

        Optional<Passenger> foundPassenger = passengerRepository.findByEmailIgnoreCase("CARLOS.GOMEZ@TEST.COM");

        assertThat(foundPassenger).isPresent();
        assertThat(foundPassenger.get().getFullName()).isEqualTo("Carlos Gomez");
    }

    @Test
    @DisplayName("Debe buscar un pasajero por email precargando su perfil")
    void shouldFindByEmailWithProfile() {
        PassengerProfile profile = new PassengerProfile();
        profile.setPhone("3109876543");
        profile.setCountryCode("MX");
        profileRepository.save(profile);

        Passenger passenger = new Passenger();
        passenger.setFullName("Laura Mora");
        passenger.setEmail("laura.mora@test.com");
        passenger.setProfile(profile);
        passengerRepository.save(passenger);

        Optional<Passenger> foundPassenger = passengerRepository.findByEmailIgnoreCaseWithProfile("laura.mora@test.com");

        assertThat(foundPassenger).isPresent();
        assertThat(foundPassenger.get().getProfile()).isNotNull();
        assertThat(foundPassenger.get().getProfile().getCountryCode()).isEqualTo("MX");
    }
}