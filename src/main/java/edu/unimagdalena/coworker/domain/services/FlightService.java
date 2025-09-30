package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.FlightDTO.FlightResponseDTO;
import edu.unimagdalena.coworker.domain.dto.FlightDTO.CreateFlightDTO;
import edu.unimagdalena.coworker.domain.dto.FlightDTO.UpdateFlightDTO;

// Flight
public interface FlightService {
    FlightResponseDTO create(CreateFlightDTO createFlightDTO);
    FlightResponseDTO getById(Long id);
    List<FlightResponseDTO> getAll();
    void delete(Long id);
    FlightResponseDTO update(Long id, UpdateFlightDTO updateFlightDTO);
}
