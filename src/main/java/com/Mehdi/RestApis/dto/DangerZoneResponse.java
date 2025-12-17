package com.Mehdi.RestApis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DangerZoneResponse {

    private Long id;
    private String title;
    private String message;
    private double latitude;
    private double longitude;
    private double radiusMeters;
    private String severity;
}