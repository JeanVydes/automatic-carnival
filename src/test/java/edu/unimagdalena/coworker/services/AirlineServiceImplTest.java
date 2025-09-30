package edu.unimagdalena.coworker.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.unimagdalena.coworker.dto.AirlineDTO;
import edu.unimagdalena.coworker.dto.AirlineDTO.AirlineResponseDTO;
import edu.unimagdalena.coworker.domain.entities.Airline;
import edu.unimagdalena.coworker.exception.NotFoundException;
import edu.unimagdalena.coworker.domain.repositories.AirlineRepository;

@ExtendWith(MockitoExtension.class)
class AirlineServiceImplTest {

    @Mock
    private AirlineRepository repos;

    @InjectMocks
    private AirlineServiceImpl service;

    @Test
    void shouldCreateAndReturnResponse() {
        var req = new AirlineDTO.CreateAirlineDTO("AirTest", "AT");

        when(repos.save(any())).thenAnswer(inv -> {
            Airline a = inv.getArgument(0);
            a.setId(1L);
            return a;
        });

        AirlineResponseDTO res = service.create(req);

        assertThat(res.id()).isEqualTo(1L);
        assertThat(res.code()).isEqualTo("AT");
        assertThat(res.name()).isEqualTo("AirTest");
        verify(repos).save(any(Airline.class));
    }

    @Test
    void shouldGetExistingAirline() {
        var airline = new Airline();
        airline.setId(5L);
        airline.setCode("EX");
        airline.setName("ExistingAir");

        when(repos.findById(5L)).thenReturn(Optional.of(airline));

        AirlineResponseDTO res = service.getById(5L);

        assertThat(res.id()).isEqualTo(5L);
        assertThat(res.code()).isEqualTo("EX");
        assertThat(res.name()).isEqualTo("ExistingAir");
    }

    @Test
    void shouldThrowWhenAirlineNotFound() {
        when(repos.findById(99L)).thenReturn(Optional.empty());

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> service.getById(99L))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Airline 99 not found");
    }

    @Test
    void shouldUpdateAirlineName() {
        var airline = new Airline();
        airline.setId(7L);
        airline.setCode("UPD");
        airline.setName("OldName");

        when(repos.findById(7L)).thenReturn(Optional.of(airline));

        var updatedDto = new AirlineDTO.UpdateAirlineDTO("NewName", "UPD");
        when(repos.save(any())).thenAnswer(inv -> inv.getArgument(0));
        AirlineResponseDTO res = service.update(7L, updatedDto);

        assertThat(res.id()).isEqualTo(7L);
        assertThat(res.code()).isEqualTo("UPD");
        assertThat(res.name()).isEqualTo("NewName");
    }

    @Test
    void shouldListAllAirlines() {
        var airlines = List.of(
                new Airline() {
                    {
                        setId(1L);
                        setCode("A1");
                        setName("AirOne");
                    }
                },
                new Airline() {
                    {
                        setId(2L);
                        setCode("A2");
                        setName("AirTwo");
                    }
                });

        when(repos.findAll()).thenReturn(airlines);

        List<AirlineResponseDTO> res = service.getAll();

        assertThat(res).hasSize(2);
        assertThat(res.get(0).code()).isEqualTo("A1");
        assertThat(res.get(0).name()).isEqualTo("AirOne");
        assertThat(res.get(1).code()).isEqualTo("A2");
        assertThat(res.get(1).name()).isEqualTo("AirTwo");
    }

    @Test
    void shouldDeleteAirline() {
        service.delete(3L);
        verify(repos).deleteById(3L);
    }
}