package com.example.cars.api.service;

import com.example.cars.api.service.domain.dto.CarDTO;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = CarsApiServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarApiControllerTest {

    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<CarDTO> getCar(String url) {
        return rest.getForEntity(url, CarDTO.class);
    }

    private ResponseEntity<List<CarDTO>> getCars(String url) {
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarDTO>>() {}
        );
    }

    @Test
    public void WhenSendingARequestToMethodGetAllCarsThenItShouldReturnAllCars(){
        List<CarDTO> cars = getCars("/api/v1/car").getBody();
        Assertions.assertNotNull(cars);
        Assertions.assertEquals(30, cars.size());
    }


}
