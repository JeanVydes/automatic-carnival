package edu.unimagdalena.coworker.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.domain.dto.AirportDTO.AirportResponseDTO;
import edu.unimagdalena.coworker.domain.dto.AirportDTO.CreateAirportDTO;
import edu.unimagdalena.coworker.domain.dto.AirportDTO.UpdateAirportDTO;
import edu.unimagdalena.coworker.domain.mappers.AirportMapper;
import edu.unimagdalena.coworker.domain.repositories.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Airport
@Service
@RequiredArgsConstructor
@Transactional
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper mapper;

    @Override
    public AirportResponseDTO create(CreateAirportDTO createAirportDTO) {
        return mapper.toResponseDTO(airportRepository.save(mapper.toEntity(createAirportDTO)));
    }

    @Override
    public AirportResponseDTO getById(Long id) {
        return mapper.toResponseDTO(airportRepository.findById(id).orElseThrow());
    }

    @Override
    public List<AirportResponseDTO> getAll() {
        return airportRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public AirportResponseDTO update(Long id, UpdateAirportDTO updateAirportDTO) {
        return airportRepository.findById(id)
                .map(airport -> {
                    return mapper.toResponseDTO(airportRepository.save(airport));
                })
                .orElseThrow();
    }
}
