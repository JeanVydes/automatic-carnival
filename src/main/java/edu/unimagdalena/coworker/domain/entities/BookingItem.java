package edu.unimagdalena.coworker.domain.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

import edu.unimagdalena.coworker.domain.enums.Cabin;
import lombok.*;

@Entity
@Table(name = "bookings_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Cabin cabin;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer segmentOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
}
