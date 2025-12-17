package com.Mehdi.RestApis.repo;

import com.Mehdi.RestApis.model.DangerZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DangerZoneRepository extends JpaRepository<DangerZone, Long> {

    List<DangerZone> findByActiveTrue();
}