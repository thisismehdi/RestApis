package com.Mehdi.RestApis.service;

import com.Mehdi.RestApis.dto.PoiResponse;
import com.Mehdi.RestApis.model.Poi;
import com.Mehdi.RestApis.repo.PoiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PoiService {

    private final PoiRepository poiRepository;

    public PoiService(PoiRepository poiRepository) {
        this.poiRepository = poiRepository;
    }

    public List<PoiResponse> getNearbyPois(
            double lat,
            double lng,
            String lang,
            double maxDistanceKm
    ) {
        return poiRepository.findByActiveTrue().stream()
                .filter(poi -> distanceKm(lat, lng, poi.getLatitude(), poi.getLongitude()) <= maxDistanceKm)
                .map(poi -> toResponse(poi, lang))
                .collect(Collectors.toList());
    }

    public PoiResponse getById(Long id, String lang) {
        Poi poi = poiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("POI not found"));

        return toResponse(poi, lang);
    }

    private PoiResponse toResponse(Poi poi, String lang) {
        boolean fr = "fr".equalsIgnoreCase(lang);

        return PoiResponse.builder()
                .id(poi.getId())
                .name(fr ? poi.getNameFr() : poi.getNameEn())
                .description(fr ? poi.getDescriptionFr() : poi.getDescriptionEn())
                .latitude(poi.getLatitude())
                .longitude(poi.getLongitude())
                .radiusMeters(poi.getRadiusMeters())
                .type(poi.getType())
                .build();
    }

    // Haversine formula
    private double distanceKm(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}