package com.smartparking.smartparking.controller;

import com.smartparking.smartparking.entity.Area;
import com.smartparking.smartparking.entity.Booking;
import com.smartparking.smartparking.entity.City;
import com.smartparking.smartparking.entity.Slot;
import com.smartparking.smartparking.repository.AreaRepository;
import com.smartparking.smartparking.repository.BookingRepository;
import com.smartparking.smartparking.repository.CityRepository;
import com.smartparking.smartparking.repository.SlotRepository;
import com.smartparking.smartparking.service.SlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotService slotService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin_dashboard";
    }

    @GetMapping("/add-city")
    public String showAddCityForm(Model model) {
        model.addAttribute("city", new City());
        return "add_city";
    }

    @PostMapping("/add-city")
    public String addCity(@ModelAttribute City city) {
        cityRepository.save(city);
        return "redirect:/admin/dashboard?city_added";
    }

    @GetMapping("/add-area")
    public String showAddAreaForm(Model model) {
        model.addAttribute("area", new Area());
        model.addAttribute("cities", cityRepository.findAll());
        return "add_area";
    }

    @PostMapping("/add-area")
    public String addArea(@ModelAttribute Area area) {
        areaRepository.save(area);
        return "redirect:/admin/dashboard?area_added";
    }

    @GetMapping("/add-slot")
    public String showAddSlotForm(Model model) {
        model.addAttribute("slot", new Slot());
        model.addAttribute("areas", areaRepository.findAll());
        return "add_slot";
    }

    @PostMapping("/add-slot")
    public String addSlot(@ModelAttribute Slot slot) {
        slot.setAvailable(true);
        slotRepository.save(slot);
        return "redirect:/admin/dashboard?slot_added";
    }

    @GetMapping("/view-slots")
    public String viewAllSlots(Model model) {
        List<Slot> allSlots = slotService.getAllSlots();
        List<Booking> bookings = bookingRepository.findByCancelledFalse();

        Map<Long, Booking> bookingMap = bookings.stream()
                .collect(Collectors.toMap(
                        b -> b.getSlot().getId(),
                        b -> b,
                        (b1, b2) -> b1
                ));

        model.addAttribute("slots", allSlots);
        model.addAttribute("bookingMap", bookingMap);

        return "view_slots";
    }

}

