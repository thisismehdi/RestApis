package com.Mehdi.RestApis.controller;

import com.Mehdi.RestApis.dto.DangerZoneResponse;
import com.Mehdi.RestApis.service.DangerZoneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/danger-zones")
public class DangerZoneController {

    private final DangerZoneService service;

    public DangerZoneController(DangerZoneService service) {
        this.service = service;
    }

    @GetMapping
    public List<DangerZoneResponse> getNearbyDangerZones(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "5") double radiusKm
    ) {
        return service.getNearby(lat, lng, lang, radiusKm);
    }
}