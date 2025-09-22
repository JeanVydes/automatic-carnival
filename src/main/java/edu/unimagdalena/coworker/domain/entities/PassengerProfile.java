package edu.unimagdalena.coworker.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passengers_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phone;
    private String countryCode;

    @OneToOne(mappedBy = "profile")
    private Passenger passenger;
}
