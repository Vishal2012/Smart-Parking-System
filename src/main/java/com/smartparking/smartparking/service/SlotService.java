package com.smartparking.smartparking.service;

import com.smartparking.smartparking.entity.Slot;

import java.util.List;

public interface SlotService {
    List<Slot> getAvailableSlots(Long areaId);
    Slot bookSlot(Long slotId, String carNumber);

    List<Slot> getAllSlots();
}
