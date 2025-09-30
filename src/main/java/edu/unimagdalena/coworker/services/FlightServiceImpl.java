package edu.unimagdalena.coworker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.dto.FlightDTO.FlightResponseDTO;
import edu.unimagdalena.coworker.dto.FlightDTO.CreateFlightDTO;
import edu.unimagdalena.coworker.dto.FlightDTO.UpdateFlightDTO;
import edu.unimagdalena.coworker.mappers.FlightMapper;
import edu.unimagdalena.coworker.domain.repositories.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Flight
@Service
@RequiredArgsConstructor
@Transactional
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper mapper;

    @Override
    public FlightResponseDTO create(CreateFlightDTO createFlightDTO) {
        return mapper.toResponseDTO(flightRepository.save(mapper.toEntity(createFlightDTO)));
    }

    @Override
    public FlightResponseDTO getById(Long id) {
        return mapper.toResponseDTO(flightRepository.findById(id).orElseThrow());
    }

    @Override
    public List<FlightResponseDTO> getAll() {
        return flightRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public FlightResponseDTO update(Long id, UpdateFlightDTO updateFlightDTO) {
        return flightRepository.findById(id)
                .map(flight -> {
                    return mapper.toResponseDTO(flightRepository.save(flight));
                })
                .orElseThrow();
    }
}
