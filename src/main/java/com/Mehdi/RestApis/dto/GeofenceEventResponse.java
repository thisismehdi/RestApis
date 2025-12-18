package com.Mehdi.RestApis.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class GeofenceEventResponse {

    private Long id;
    private String eventType;
    private String zoneType;
    private Long zoneId;
    private LocalDateTime eventTime;
}