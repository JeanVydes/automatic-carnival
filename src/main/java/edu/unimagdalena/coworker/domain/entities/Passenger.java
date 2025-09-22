package edu.unimagdalena.coworker.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passenger {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 128)
    private String fullName;

    private String email;
    @OneToOne @JoinColumn(name = "profile_id", unique = true)
    private PassengerProfile profile;

}
