package com.smartparking.smartparking.service.impl;

import com.smartparking.smartparking.entity.Booking;
import com.smartparking.smartparking.entity.Slot;
import com.smartparking.smartparking.entity.User;
import com.smartparking.smartparking.repository.BookingRepository;
import com.smartparking.smartparking.repository.SlotRepository;
import com.smartparking.smartparking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Override
    public List<Booking> getActiveBookingsForUser(User user) {
        return bookingRepository.findByUserAndCancelledFalse(user);
    }

    @Override
    public List<Booking> getAllBookingsForUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setCancelled(true);


        Slot slot = booking.getSlot();
        slot.setAvailable(true);
        slot.setCarNumber(null);

        slotRepository.save(slot);
        bookingRepository.save(booking);
    }
}
