package com.Mehdi.RestApis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PoiResponse {

    private Long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private double radiusMeters;
    private String type;
}