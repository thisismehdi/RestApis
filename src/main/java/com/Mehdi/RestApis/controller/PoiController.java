package com.Mehdi.RestApis.controller;

import com.Mehdi.RestApis.dto.PoiResponse;
import com.Mehdi.RestApis.service.PoiService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pois")
public class PoiController {

    private final PoiService poiService;

    public PoiController(PoiService poiService) {
        this.poiService = poiService;
    }

    // 📍 Nearby POIs
    @GetMapping
    public List<PoiResponse> getNearbyPois(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "5") double radiusKm
    ) {
        return poiService.getNearbyPois(lat, lng, lang, radiusKm);
    }

    // 📄 POI details
    @GetMapping("/{id}")
    public PoiResponse getPoiById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return poiService.getById(id, lang);
    }
}
