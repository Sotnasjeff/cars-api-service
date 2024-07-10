package com.example.cars.api.service.controller;

import com.example.cars.api.service.domain.dto.CarDTO;
import com.example.cars.api.service.domain.entity.Car;
import com.example.cars.api.service.domain.service.CarService;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    @Autowired
    public CarService service;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getCars() {
        return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id")Long id) {
        return service.getCarById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CarDTO>> getCarByType(@PathVariable("type")String type) {
        List<CarDTO> car = service.getCarByType(type);

        return car.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(car);
    }

    @PostMapping
    public Car insertCar(@RequestBody Car car){
        return service.insertCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id")Long id, @RequestBody Car car) {
        return service.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id")Long id){
        return service.deleteCar(id);
    }
}
