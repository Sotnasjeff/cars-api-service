package com.example.cars.api.service.domain.controller;

import com.example.cars.api.service.domain.dto.CarDTO;
import com.example.cars.api.service.domain.entity.Car;
import com.example.cars.api.service.domain.service.CarService;
import com.example.cars.api.service.domain.utils.UriCatcher;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    UriCatcher uriCatcher;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getCars() {
        return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getCarById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CarDTO>> getCarByType(@PathVariable("type") String type) {
        List<CarDTO> car = service.getCarByType(type);

        return car.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(car);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CarDTO> insertCar(@RequestBody Car car) {

        CarDTO newCar = service.insertCar(car);

        URI location = uriCatcher.getUri(newCar.getId());
        return ResponseEntity.created(location).build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("id") Long id, @RequestBody Car car) {
        CarDTO updatedCar = service.updateCar(id, car);
        return updatedCar != null ? ResponseEntity.ok(updatedCar) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable("id") Long id) {
        service.deleteCar(id);
        return ResponseEntity.ok().build();
    }
}
