package com.smartparking.smartparking.service;

import com.smartparking.smartparking.entity.Booking;
import com.smartparking.smartparking.entity.User;

import java.util.List;

public interface BookingService {
    List<Booking> getActiveBookingsForUser(User user);
    List<Booking> getAllBookingsForUser(User user);
    void cancelBooking(Long bookingId);
}
