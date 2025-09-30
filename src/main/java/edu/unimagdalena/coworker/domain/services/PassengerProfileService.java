package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.PassengerProfileResponseDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.CreatePassengerProfileDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.UpdatePassengerProfileDTO;

// PassengerProfile
public interface PassengerProfileService {
    PassengerProfileResponseDTO create(CreatePassengerProfileDTO createPassengerProfileDTO);
    PassengerProfileResponseDTO getById(Long id);
    List<PassengerProfileResponseDTO> getAll();
    void delete(Long id);
    PassengerProfileResponseDTO update(Long id, UpdatePassengerProfileDTO updatePassengerProfileDTO);
}
