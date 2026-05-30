package com.smartparking.smartparking.service;

import com.smartparking.smartparking.entity.User;

public interface UserService {
    User saveUser(User user);
    User findByUsername(String username);
}
