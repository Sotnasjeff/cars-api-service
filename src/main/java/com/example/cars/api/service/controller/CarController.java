package com.example.cars.api.service.controller;

import com.example.cars.api.service.domain.dto.CarDTO;
import com.example.cars.api.service.domain.entity.Car;
import com.example.cars.api.service.domain.service.CarService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id) {
        return service.getCarById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CarDTO>> getCarByType(@PathVariable("type") String type) {
        List<CarDTO> car = service.getCarByType(type);

        return car.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<CarDTO> insertCar(@RequestBody Car car) {
        try {
            CarDTO newCar = service.insertCar(car);

            URI location = getUri(newCar.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        CarDTO updatedCar =service.updateCar(id, car);
        return updatedCar != null ? ResponseEntity.ok(updatedCar) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable("id") Long id) {
        return service.deleteCar(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }
}
