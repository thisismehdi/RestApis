package com.Mehdi.RestApis.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "danger_zones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DangerZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleEn;
    private String titleFr;

    @Column(length = 2000)
    private String messageEn;

    @Column(length = 2000)
    private String messageFr;

    private double latitude;
    private double longitude;
    private double radiusMeters;

    // LOW | MEDIUM | HIGH | CRITICAL
    private String severity;

    private boolean active = true;
}