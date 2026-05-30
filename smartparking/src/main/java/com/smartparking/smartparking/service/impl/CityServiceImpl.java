package com.smartparking.smartparking.service.impl;

import com.smartparking.smartparking.entity.City;
import com.smartparking.smartparking.repository.CityRepository;
import com.smartparking.smartparking.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
