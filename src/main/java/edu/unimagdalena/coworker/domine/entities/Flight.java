package edu.unimagdalena.coworker.domine.entities;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false)
    private OffsetDateTime departureAt;

    @Column(nullable = false)
    private OffsetDateTime arrivalAt;

    @ManyToOne(optional = false)
    private Airline airline;

    @ManyToOne(optional = false)
    private Airport origin;
    @ManyToOne(optional = false)
    private Airport destination;

    @OneToMany(mappedBy = "flight")
    private Set<SeatInventory> seatInventories = new HashSet<>();

    @OneToMany(mappedBy = "flight")
    private Set<BookingItem> bookingItems = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "flights_tags", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getFlights().add(this);
    }
}
