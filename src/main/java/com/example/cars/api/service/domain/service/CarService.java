package com.example.cars.api.service.domain.service;

import com.example.cars.api.service.domain.dto.CarDTO;
import com.example.cars.api.service.domain.entity.Car;
import com.example.cars.api.service.domain.repository.CarRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public List<CarDTO> getCars() {
        return repository.findAll().stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public Optional<CarDTO> getCarById(Long id) {
        return repository.findById(id).map(CarDTO::create);
    }

    public List<CarDTO> getCarByType(String type) {
        return repository.findByType(type).stream().map(CarDTO::create).collect(Collectors.toList());
    }

    public CarDTO insertCar(Car car) {
        return CarDTO.create(repository.save(car));
    }

    public CarDTO updateCar(Long id, Car car) {
        Assert.notNull(id, "Id can not be null");
        return CarDTO.create(repository.findById(id).map(db -> {
            db.setName(car.getName());
            db.setType(car.getType());

            repository.save(db);

            return db;
        }).orElseThrow(() -> new RuntimeException("It was not possible to update due to an error")));
    }

    public Boolean deleteCar(Long id) {
        if (getCarById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
