package com.smartparking.smartparking.service.impl;

import com.smartparking.smartparking.entity.Booking;
import com.smartparking.smartparking.entity.Slot;
import com.smartparking.smartparking.entity.User;
import com.smartparking.smartparking.repository.BookingRepository;
import com.smartparking.smartparking.repository.SlotRepository;
import com.smartparking.smartparking.repository.UserRepository;
import com.smartparking.smartparking.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Slot> getAvailableSlots(Long areaId) {
        return slotRepository.findByAreaIdAndAvailableTrue(areaId);
    }

    @Override
    public Slot bookSlot(Long slotId, String carNumber) {
        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.setAvailable(false);
        slot.setCarNumber(carNumber);
        slotRepository.save(slot);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username);

        Booking booking = new Booking();
        booking.setSlot(slot);
        booking.setUser(user);
        booking.setCarNumber(carNumber);
        booking.setBookingTime(LocalDateTime.now());
        booking.setCancelled(false);

        bookingRepository.save(booking);

        return slot;
    }

    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }
}
