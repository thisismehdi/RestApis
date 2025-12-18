package com.Mehdi.RestApis.service;

import com.Mehdi.RestApis.dto.GeofenceEventRequest;
import com.Mehdi.RestApis.dto.GeofenceEventResponse;
import com.Mehdi.RestApis.model.GeofenceEvent;
import com.Mehdi.RestApis.model.User;
import com.Mehdi.RestApis.repo.GeofenceEventRepository;
import com.Mehdi.RestApis.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeofenceEventService {

    private final GeofenceEventRepository repository;
    private final UserRepository userRepository;

    public GeofenceEventService(
            GeofenceEventRepository repository,
            UserRepository userRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public void saveEvent(GeofenceEventRequest request, Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        GeofenceEvent event = GeofenceEvent.builder()
                .eventType(request.getEventType())
                .zoneType(request.getZoneType())
                .zoneId(request.getZoneId())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .eventTime(LocalDateTime.now())
                .user(user)
                .build();

        repository.save(event);
    }

    public List<GeofenceEventResponse> getUserEvents(Authentication auth) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return repository.findByUserOrderByEventTimeDesc(user)
                .stream()
                .map(e -> GeofenceEventResponse.builder()
                        .id(e.getId())
                        .eventType(e.getEventType())
                        .zoneType(e.getZoneType())
                        .zoneId(e.getZoneId())
                        .eventTime(e.getEventTime())
                        .build())
                .collect(Collectors.toList());
    }
}