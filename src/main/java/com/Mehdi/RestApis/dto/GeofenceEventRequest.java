package com.Mehdi.RestApis.dto;

import lombok.Data;

@Data
public class GeofenceEventRequest {

    private String eventType;   // ENTER | EXIT
    private String zoneType;    // DANGER_ZONE | POI
    private Long zoneId;

    private double latitude;
    private double longitude;
}