package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.SeatInventoryDTO.SeatInventoryResponseDTO;
import edu.unimagdalena.coworker.domain.dto.SeatInventoryDTO.CreateSeatInventoryDTO;
import edu.unimagdalena.coworker.domain.dto.SeatInventoryDTO.UpdateSeatInventoryDTO;

// SeatInventory
public interface SeatInventoryService {
    SeatInventoryResponseDTO create(CreateSeatInventoryDTO createSeatInventoryDTO);
    SeatInventoryResponseDTO getById(Long id);
    List<SeatInventoryResponseDTO> getAll();
    void delete(Long id);
    SeatInventoryResponseDTO update(Long id, UpdateSeatInventoryDTO updateSeatInventoryDTO);
}
