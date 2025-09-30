package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.PassengerDTO.PassengerResponseDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerDTO.CreatePassengerDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerDTO.UpdatePassengerDTO;

// Passenger
public interface PassengerService {
    PassengerResponseDTO create(CreatePassengerDTO createPassengerDTO);
    PassengerResponseDTO getById(Long id);
    List<PassengerResponseDTO> getAll();
    void delete(Long id);
    PassengerResponseDTO update(Long id, UpdatePassengerDTO updatePassengerDTO);
}
