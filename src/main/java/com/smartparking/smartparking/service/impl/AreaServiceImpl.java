package com.smartparking.smartparking.service.impl;

import com.smartparking.smartparking.entity.Area;
import com.smartparking.smartparking.repository.AreaRepository;
import com.smartparking.smartparking.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public List<Area> getAreasByCity(Long cityId) {
        return areaRepository.findByCityId(cityId);
    }
}
