package com.Mehdi.RestApis.repo;

import com.Mehdi.RestApis.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoiRepository extends JpaRepository<Poi, Long> {

    List<Poi> findByActiveTrue();
}