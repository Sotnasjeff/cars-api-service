package com.example.cars.api.service;

import com.example.cars.api.service.controller.CarController;
import com.example.cars.api.service.domain.dto.CarDTO;
import com.example.cars.api.service.domain.entity.Car;
import com.example.cars.api.service.domain.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class CarsApiServiceApplicationTests {

	@Autowired
	private CarService service;

	@Autowired
	private CarController controller;

	@Test
	public void GivenARequestToInsertThanItShouldInsertSucessfully(){
		Car newCar = new Car("Car_test", "Esportivo");

		ResponseEntity<CarDTO> response = controller.insertCar(newCar);

		Assertions.assertEquals("201 CREATED",response.getStatusCode().toString());

	}

	@Test
	void contextLoads() {
	}

}
