package com.smartparking.smartparking.controller;

import com.smartparking.smartparking.entity.User;
import com.smartparking.smartparking.service.BookingService;
import com.smartparking.smartparking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/my-bookings")
    public String viewBookings(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());

        model.addAttribute("allBookings", bookingService.getAllBookingsForUser(user));
        return "booking";
    }

    @GetMapping("/cancel-booking/{id}")
    public String cancelBooking(@PathVariable("id") Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/my-bookings?cancel=success";
    }
}
