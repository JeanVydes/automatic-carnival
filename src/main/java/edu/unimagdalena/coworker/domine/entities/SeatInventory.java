package edu.unimagdalena.coworker.domine.entities;

import edu.unimagdalena.coworker.domine.enums.Cabin;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seat_inventories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Cabin cabin;
    private Integer totalSeats;
    private Integer availableSeats;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
}
