package com.Mehdi.RestApis.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "geofence_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeofenceEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ENTER | EXIT
    private String eventType;

    // DANGER_ZONE | POI
    private String zoneType;

    private Long zoneId;

    private double latitude;
    private double longitude;

    private LocalDateTime eventTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}