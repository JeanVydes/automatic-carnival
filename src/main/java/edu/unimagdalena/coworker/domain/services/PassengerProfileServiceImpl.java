package edu.unimagdalena.coworker.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.PassengerProfileResponseDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.CreatePassengerProfileDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.UpdatePassengerProfileDTO;
import edu.unimagdalena.coworker.domain.mappers.PassengerProfileMapper;
import edu.unimagdalena.coworker.domain.repositories.PassengerProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// PassengerProfile
@Service
@RequiredArgsConstructor
@Transactional
public class PassengerProfileServiceImpl implements PassengerProfileService {
    private final PassengerProfileRepository passengerProfileRepository;
    private final PassengerProfileMapper mapper;

    @Override
    public PassengerProfileResponseDTO create(CreatePassengerProfileDTO createPassengerProfileDTO) {
        return mapper.toResponseDTO(passengerProfileRepository.save(mapper.toEntity(createPassengerProfileDTO)));
    }

    @Override
    public PassengerProfileResponseDTO getById(Long id) {
        return mapper.toResponseDTO(passengerProfileRepository.findById(id).orElseThrow());
    }

    @Override
    public List<PassengerProfileResponseDTO> getAll() {
        return passengerProfileRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        passengerProfileRepository.deleteById(id);
    }

    @Override
    public PassengerProfileResponseDTO update(Long id, UpdatePassengerProfileDTO updatePassengerProfileDTO) {
        return passengerProfileRepository.findById(id)
                .map(passengerProfile -> {
                    return mapper.toResponseDTO(passengerProfileRepository.save(passengerProfile));
                })
                .orElseThrow();
    }
}
