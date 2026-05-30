package com.smartparking.smartparking.repository;

import com.smartparking.smartparking.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByCityId(Long cityId);
}
