package edu.unimagdalena.coworker.services;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import edu.unimagdalena.coworker.dto.FlightDTO.CreateFlightDTO;
import edu.unimagdalena.coworker.dto.FlightDTO.UpdateFlightDTO;
import edu.unimagdalena.coworker.domain.entities.Airline;
import edu.unimagdalena.coworker.domain.entities.Airport;
import edu.unimagdalena.coworker.domain.entities.Flight;
import edu.unimagdalena.coworker.domain.repositories.AirlineRepository;
import edu.unimagdalena.coworker.domain.repositories.AirportRepository;
import edu.unimagdalena.coworker.domain.repositories.FlightRepository;

import java.time.OffsetDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;
    @Mock
    private AirportRepository airportRepository;
    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    private Airport origin;
    private Airport destination;
    private Airline airline;
    private Flight flight;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        origin = Airport.builder().id(1L).code("BOG").build();
        destination = Airport.builder().id(2L).code("MDE").build();
        airline = Airline.builder().id(1L).name("Avianca").build();

        flight = Flight.builder()
                .id(10L)
                .origin(origin)
                .destination(destination)
                .airline(airline)
                .departureAt(OffsetDateTime.parse("2025-09-30T10:00:00Z"))
                .arrivalAt(OffsetDateTime.parse("2025-09-30T12:00:00Z"))
                .build();
    }

    @Test
    void testCreateFlightSuccess() {
        CreateFlightDTO dto = new CreateFlightDTO(
                "BOG", "MDE", "2025-09-30T10:00:00Z", "2025-09-30T12:00:00Z");

        when(airportRepository.findByCode("BOG")).thenReturn(Optional.of(origin));
        when(airportRepository.findByCode("MDE")).thenReturn(Optional.of(destination));
        when(airlineRepository.findAll()).thenReturn(List.of(airline));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        var response = flightService.create(dto);

        assertNotNull(response);
        assertEquals("BOG", response.origin());
        assertEquals("MDE", response.destination());
        assertEquals("2025-09-30T10:00Z", response.departureTime());
    }

    @Test
    void testCreateFlightOriginNotFound() {
        CreateFlightDTO dto = new CreateFlightDTO("XXX", "MDE", "2025-09-30T10:00:00Z", "2025-09-30T12:00:00Z");
        when(airportRepository.findByCode("XXX")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> flightService.create(dto));
    }

    @Test
    void testGetFlightSuccess() {
        when(flightRepository.findById(10L)).thenReturn(Optional.of(flight));

        var response = flightService.getById(10L);

        assertEquals(10L, response.id());
        assertEquals("BOG", response.origin());
        assertEquals("MDE", response.destination());
    }

    @Test
    void testGetFlightNotFound() {
        when(flightRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> flightService.getById(99L));
    }

    @Test
    void testListFlights() {
        when(flightRepository.findAll()).thenReturn(List.of(flight));

        var responses = flightService.getAll();

        assertEquals(1, responses.size());
        assertEquals("BOG", responses.get(0).origin());
    }

    @Test
    void testUpdateFlightSuccess() {
        UpdateFlightDTO dto = new UpdateFlightDTO(
                10L, "MDE", "BOG", "2025-09-30T15:00:00Z", "2025-09-30T17:00:00Z");

        when(flightRepository.findById(10L)).thenReturn(Optional.of(flight));
        when(airportRepository.findByCode("MDE")).thenReturn(Optional.of(destination));
        when(airportRepository.findByCode("BOG")).thenReturn(Optional.of(origin));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        var response = flightService.update(10L, dto);

        assertNotNull(response);
        assertEquals("MDE", response.origin());
        assertEquals("BOG", response.destination());
    }

    @Test
    void testDeleteFlightSuccess() {
        when(flightRepository.existsById(10L)).thenReturn(true);
        doNothing().when(flightRepository).deleteById(10L);

        flightService.delete(10L);

        verify(flightRepository, times(1)).deleteById(10L);
    }

    @Test
    void testDeleteFlightNotFound() {
        when(flightRepository.existsById(99L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> flightService.delete(99L));
    }
}