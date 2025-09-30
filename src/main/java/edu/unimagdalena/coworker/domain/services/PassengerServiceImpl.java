package edu.unimagdalena.coworker.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.domain.dto.PassengerDTO.PassengerResponseDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerDTO.CreatePassengerDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerDTO.UpdatePassengerDTO;
import edu.unimagdalena.coworker.domain.mappers.PassengerMapper;
import edu.unimagdalena.coworker.domain.repositories.PassengerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Passenger
@Service
@RequiredArgsConstructor
@Transactional
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper mapper;

    @Override
    public PassengerResponseDTO create(CreatePassengerDTO createPassengerDTO) {
        return mapper.toResponseDTO(passengerRepository.save(mapper.toEntity(createPassengerDTO)));
    }

    @Override
    public PassengerResponseDTO getById(Long id) {
        return mapper.toResponseDTO(passengerRepository.findById(id).orElseThrow());
    }

    @Override
    public List<PassengerResponseDTO> getAll() {
        return passengerRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public PassengerResponseDTO update(Long id, UpdatePassengerDTO updatePassengerDTO) {
        return passengerRepository.findById(id)
                .map(Passenger -> {
                    return mapper.toResponseDTO(passengerRepository.save(Passenger));
                })
                .orElseThrow();
    }
}
