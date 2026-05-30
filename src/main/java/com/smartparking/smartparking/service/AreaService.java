package com.smartparking.smartparking.service;

import com.smartparking.smartparking.entity.Area;

import java.util.List;

public interface AreaService {
    List<Area> getAreasByCity(Long cityId);
}
