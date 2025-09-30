package edu.unimagdalena.coworker.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.domain.dto.SeatInventoryDTO.SeatInventoryResponseDTO;
import edu.unimagdalena.coworker.domain.dto.SeatInventoryDTO.CreateSeatInventoryDTO;
import edu.unimagdalena.coworker.domain.dto.SeatInventoryDTO.UpdateSeatInventoryDTO;
import edu.unimagdalena.coworker.domain.mappers.SeatInventoryMapper;
import edu.unimagdalena.coworker.domain.repositories.SeatInventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// SeatInventory
@Service
@RequiredArgsConstructor
@Transactional
public class SeatInventoryServiceImpl implements SeatInventoryService {
    private final SeatInventoryRepository seatInventoryRepository;
    private final SeatInventoryMapper mapper;

    @Override
    public SeatInventoryResponseDTO create(CreateSeatInventoryDTO createSeatInventoryDTO) {
        return mapper.toResponseDTO(seatInventoryRepository.save(mapper.toEntity(createSeatInventoryDTO)));
    }

    @Override
    public SeatInventoryResponseDTO getById(Long id) {
        return mapper.toResponseDTO(seatInventoryRepository.findById(id).orElseThrow());
    }

    @Override
    public List<SeatInventoryResponseDTO> getAll() {
        return seatInventoryRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        seatInventoryRepository.deleteById(id);
    }

    @Override
    public SeatInventoryResponseDTO update(Long id, UpdateSeatInventoryDTO updateSeatInventoryDTO) {
        return seatInventoryRepository.findById(id)
                .map(seatInventory -> {
                    return mapper.toResponseDTO(seatInventoryRepository.save(seatInventory));
                })
                .orElseThrow();
    }
}
