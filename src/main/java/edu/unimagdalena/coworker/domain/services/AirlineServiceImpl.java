package edu.unimagdalena.coworker.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.domain.dto.AirlineDTO.AirlineResponseDTO;
import edu.unimagdalena.coworker.domain.dto.AirlineDTO.CreateAirlineDTO;
import edu.unimagdalena.coworker.domain.dto.AirlineDTO.UpdateAirlineDTO;
import edu.unimagdalena.coworker.domain.mappers.AirlineMapper;
import edu.unimagdalena.coworker.domain.repositories.AirlineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Airline
@Service
@RequiredArgsConstructor
@Transactional
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper mapper;

    @Override
    public AirlineResponseDTO create(CreateAirlineDTO createAirlineDTO) {
        return mapper.toResponseDTO(airlineRepository.save(mapper.toEntity(createAirlineDTO)));
    }

    @Override
    public AirlineResponseDTO getById(Long id) {
        return mapper.toResponseDTO(airlineRepository.findById(id).orElseThrow());
    }

    @Override
    public List<AirlineResponseDTO> getAll() {
        return airlineRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        airlineRepository.deleteById(id);
    }

    @Override
    public AirlineResponseDTO update(Long id, UpdateAirlineDTO updateAirlineDTO) {
        return airlineRepository.findById(id)
                .map(airline -> {
                    return mapper.toResponseDTO(airlineRepository.save(airline));
                })
                .orElseThrow();
    }
}
