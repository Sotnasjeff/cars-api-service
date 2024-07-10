package com.example.cars.api.service.domain.service;

import com.example.cars.api.service.domain.entity.Car;
import com.example.cars.api.service.domain.repository.CarRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public Iterable<Car> getCars() {
        return repository.findAll();
    }

    public List<Car> getFakeCar() {
        List<Car> listCar = new ArrayList<>();

        listCar.add(new Car(1L, "Ferrari"));
        listCar.add(new Car(2L, "Mercedes"));
        listCar.add(new Car(3L, "Redbull"));

        return listCar;
    }

    public Optional<Car> getCarById(Long id) {
        return repository.findById(id);
    }

    public List<Car> getCarByType(String type) {
        return repository.findByType(type);
    }

    public Car insertCar(Car car) {
        return repository.save(car);
    }

    public Car updateCar(Long id, Car car) {
        Assert.notNull(id, "Id can not be null");
        return getCarById(id).map(db -> {
           db.setName(car.getName());
           db.setType(car.getType());

           repository.save(db);

           return db;
        }).orElseThrow(() -> new RuntimeException("It was not possible to update due to an error"));
    }

    public String deleteCar(Long id) {
        repository.deleteById(id);

        return "Car has been deleted successfully";
    }
}
