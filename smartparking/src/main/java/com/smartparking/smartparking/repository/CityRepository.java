package com.smartparking.smartparking.repository;

import com.smartparking.smartparking.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
