package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.AirlineDTO.AirlineResponseDTO;
import edu.unimagdalena.coworker.domain.dto.AirlineDTO.CreateAirlineDTO;
import edu.unimagdalena.coworker.domain.dto.AirlineDTO.UpdateAirlineDTO;

// Airline
public interface AirlineService {
    AirlineResponseDTO create(CreateAirlineDTO createAirlineDTO);
    AirlineResponseDTO getById(Long id);
    List<AirlineResponseDTO> getAll();
    void delete(Long id);
    AirlineResponseDTO update(Long id, UpdateAirlineDTO updateAirlineDTO);
}
