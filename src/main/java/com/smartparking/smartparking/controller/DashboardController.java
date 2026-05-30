package com.smartparking.smartparking.controller;

import com.smartparking.smartparking.entity.Area;
import com.smartparking.smartparking.entity.City;
import com.smartparking.smartparking.entity.Slot;
import com.smartparking.smartparking.service.AreaService;
import com.smartparking.smartparking.service.CityService;
import com.smartparking.smartparking.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private CityService cityService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private SlotService slotService;

    @GetMapping
    public String showCities(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());

        return "cities";
    }

    @GetMapping("/areas/{cityId}")
    public String showAreas(@PathVariable Long cityId, Model model) {
        List<Area> areas = areaService.getAreasByCity(cityId);
        model.addAttribute("areas", areas);
        return "areas";
    }

    @GetMapping("/slots/{areaId}")
    public String showSlots(@PathVariable Long areaId, Model model) {
        List<Slot> slots = slotService.getAvailableSlots(areaId);
        model.addAttribute("slots", slots);
        return "slots";
    }

    @PostMapping("/book-slot")
    public String bookSlot(@RequestParam Long slotId, @RequestParam String carNumber, Model model) {
        slotService.bookSlot(slotId, carNumber);
        return "redirect:/dashboard?booked=true";
    }
}
