package edu.unimagdalena.coworker.services;

import java.util.List;

import edu.unimagdalena.coworker.dto.PassengerProfileDTO.PassengerProfileResponseDTO;
import edu.unimagdalena.coworker.dto.PassengerProfileDTO.CreatePassengerProfileDTO;
import edu.unimagdalena.coworker.dto.PassengerProfileDTO.UpdatePassengerProfileDTO;

// PassengerProfile
public interface PassengerProfileService {
    PassengerProfileResponseDTO create(CreatePassengerProfileDTO createPassengerProfileDTO);
    PassengerProfileResponseDTO getById(Long id);
    List<PassengerProfileResponseDTO> getAll();
    void delete(Long id);
    PassengerProfileResponseDTO update(Long id, UpdatePassengerProfileDTO updatePassengerProfileDTO);
}
