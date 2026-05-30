package com.smartparking.smartparking.repository;

import com.smartparking.smartparking.entity.Booking;
import com.smartparking.smartparking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCancelledFalse();
    List<Booking> findByUserAndCancelledFalse(User user);

    List<Booking> findByUser(User user);
}
