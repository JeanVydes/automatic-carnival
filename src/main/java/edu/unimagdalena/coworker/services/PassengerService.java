package edu.unimagdalena.coworker.services;

import java.util.List;

import edu.unimagdalena.coworker.dto.PassengerDTO.PassengerResponseDTO;
import edu.unimagdalena.coworker.dto.PassengerDTO.CreatePassengerDTO;
import edu.unimagdalena.coworker.dto.PassengerDTO.UpdatePassengerDTO;

// Passenger
public interface PassengerService {
    PassengerResponseDTO create(CreatePassengerDTO createPassengerDTO);
    PassengerResponseDTO getById(Long id);
    List<PassengerResponseDTO> getAll();
    void delete(Long id);
    PassengerResponseDTO update(Long id, UpdatePassengerDTO updatePassengerDTO);
}
