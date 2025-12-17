package com.Mehdi.RestApis.service;

import com.Mehdi.RestApis.dto.DangerZoneResponse;
import com.Mehdi.RestApis.model.DangerZone;
import com.Mehdi.RestApis.repo.DangerZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DangerZoneService {

    private final DangerZoneRepository repository;

    public DangerZoneService(DangerZoneRepository repository) {
        this.repository = repository;
    }

    public List<DangerZoneResponse> getNearby(
            double lat,
            double lng,
            String lang,
            double radiusKm
    ) {
        return repository.findByActiveTrue().stream()
                .filter(z -> distanceKm(lat, lng, z.getLatitude(), z.getLongitude()) <= radiusKm)
                .map(z -> toResponse(z, lang))
                .collect(Collectors.toList());
    }

    private DangerZoneResponse toResponse(DangerZone z, String lang) {
        boolean fr = "fr".equalsIgnoreCase(lang);

        return DangerZoneResponse.builder()
                .id(z.getId())
                .title(fr ? z.getTitleFr() : z.getTitleEn())
                .message(fr ? z.getMessageFr() : z.getMessageEn())
                .latitude(z.getLatitude())
                .longitude(z.getLongitude())
                .radiusMeters(z.getRadiusMeters())
                .severity(z.getSeverity())
                .build();
    }

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