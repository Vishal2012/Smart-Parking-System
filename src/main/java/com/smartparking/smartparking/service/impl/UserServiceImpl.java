package com.smartparking.smartparking.service.impl;

import com.smartparking.smartparking.entity.User;
import com.smartparking.smartparking.repository.UserRepository;
import com.smartparking.smartparking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        if (user.getRole() != null && user.getRole().startsWith("ROLE_")) {
            user.setRole(user.getRole().substring(5)); // remove "ROLE_" prefix
        }

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
