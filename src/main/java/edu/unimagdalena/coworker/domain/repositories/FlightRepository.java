package edu.unimagdalena.coworker.domain.repositories;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.unimagdalena.coworker.domain.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByAirline_Name(String airlineName);
    Page<Flight> findByOrigin_CodeAndDestination_CodeAndDepartureAtBetween(String origin, String destination,
            OffsetDateTime from, OffsetDateTime to, Pageable pageable);


    @Query("SELECT f FROM Flight f LEFT JOIN FETCH f.airline LEFT JOIN FETCH f.origin LEFT JOIN FETCH f.destination LEFT JOIN FETCH f.tags WHERE (:o IS NULL OR f.origin.code = :o) AND (:d IS NULL OR f.destination.code = :d) AND f.departureTime BETWEEN :from AND :to")
    List<Flight> findByOriginCodeAndDestinationCodeOptionalAndDepartureAtBetween(@Param("o") String origin,
            @Param("d") String destination, @Param("from") OffsetDateTime from, @Param("to") OffsetDateTime to);

    @Query(value = "SELECT f.* FROM flight f JOIN flight_tags ft ON f.id = ft.flight_id JOIN tag t ON ft.tag_id = t.id WHERE t.name IN :tagNames GROUP BY f.id HAVING COUNT(DISTINCT t.id) = :required", nativeQuery = true)
    List<Flight> findByAllTags(@Param("tagNames") Collection<String> tagNames, @Param("required") long required);
}
