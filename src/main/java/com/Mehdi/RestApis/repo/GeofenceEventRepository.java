package com.Mehdi.RestApis.repo;

import com.Mehdi.RestApis.model.GeofenceEvent;
import com.Mehdi.RestApis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeofenceEventRepository extends JpaRepository<GeofenceEvent, Long> {

    List<GeofenceEvent> findByUserOrderByEventTimeDesc(User user);
}