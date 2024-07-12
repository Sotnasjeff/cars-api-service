package com.example.cars.api.service.domain.repository;

import com.example.cars.api.service.domain.entity.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByType(String type);
}
