package edu.unimagdalena.coworker.domain.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.unimagdalena.coworker.AbstractIntegrationTest;
import edu.unimagdalena.coworker.domain.entities.PassengerProfile;

class PassengerProfileRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private PassengerProfileRepository profileRepository;

    @Test
    @DisplayName("Debe guardar y recuperar un perfil de pasajero")
    void shouldSaveAndRetrieveProfile() {
        PassengerProfile profile = new PassengerProfile();
        profile.setPhone("3001234567");
        profile.setCountryCode("CO");

        PassengerProfile savedProfile = profileRepository.save(profile);

        assertThat(savedProfile).isNotNull();
        assertThat(savedProfile.getId()).isNotNull();
        assertThat(savedProfile.getCountryCode()).isEqualTo("CO");
    }
}