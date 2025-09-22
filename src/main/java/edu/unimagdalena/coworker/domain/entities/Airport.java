package edu.unimagdalena.coworker.domain.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String code;
    private String city;

    @OneToMany(mappedBy = "origin")
    private List<Flight> flights = new ArrayList<>();
    @OneToMany(mappedBy = "destination")
    private List<Flight> arrivals = new ArrayList<>();
}
