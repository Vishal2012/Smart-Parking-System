package com.smartparking.smartparking.repository;

import com.smartparking.smartparking.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByAreaIdAndAvailableTrue(Long areaId);
}
