package com.smartparking.smartparking.controller;

import com.smartparking.smartparking.entity.User;
import com.smartparking.smartparking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return "redirect:/login?error"; // username already exists
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole().toUpperCase());

        userService.saveUser(user); // ✅ Service layer call that handles "ROLE_" cleanup

        return "redirect:/login?success";
    }
}
