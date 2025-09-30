package edu.unimagdalena.coworker.services;

import java.util.List;

import edu.unimagdalena.coworker.dto.SeatInventoryDTO.SeatInventoryResponseDTO;
import edu.unimagdalena.coworker.dto.SeatInventoryDTO.CreateSeatInventoryDTO;
import edu.unimagdalena.coworker.dto.SeatInventoryDTO.UpdateSeatInventoryDTO;

// SeatInventory
public interface SeatInventoryService {
    SeatInventoryResponseDTO create(CreateSeatInventoryDTO createSeatInventoryDTO);
    SeatInventoryResponseDTO getById(Long id);
    List<SeatInventoryResponseDTO> getAll();
    void delete(Long id);
    SeatInventoryResponseDTO update(Long id, UpdateSeatInventoryDTO updateSeatInventoryDTO);
}
