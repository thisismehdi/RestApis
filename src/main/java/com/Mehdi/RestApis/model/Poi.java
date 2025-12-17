package com.Mehdi.RestApis.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pois")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Multilingual fields
    private String nameEn;
    private String nameFr;

    @Column(length = 2000)
    private String descriptionEn;

    @Column(length = 2000)
    private String descriptionFr;

    // Geo
    private double latitude;
    private double longitude;
    private double radiusMeters;

    // Category (MUSEUM, FOOD, NATURE, etc.)
    private String type;

    private boolean active = true;
}