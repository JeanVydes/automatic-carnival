package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.AirportDTO.AirportResponseDTO;
import edu.unimagdalena.coworker.domain.dto.AirportDTO.CreateAirportDTO;
import edu.unimagdalena.coworker.domain.dto.AirportDTO.UpdateAirportDTO;

// Airport
public interface AirportService {
    AirportResponseDTO create(CreateAirportDTO createAirportDTO);
    AirportResponseDTO getById(Long id);
    List<AirportResponseDTO> getAll();
    void delete(Long id);
    AirportResponseDTO update(Long id, UpdateAirportDTO updateAirportDTO);
}
