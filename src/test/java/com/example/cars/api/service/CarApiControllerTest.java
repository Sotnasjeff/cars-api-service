package com.example.cars.api.service;

import com.example.cars.api.service.domain.dto.CarDTO;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = CarsApiServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarApiControllerTest {

    @Autowired
    protected TestRestTemplate rest;

    @Configuration
    static class TestConfig {
        @Bean
        public TestRestTemplate testRestTemplate(RestTemplateBuilder builder) {
            return new TestRestTemplate(builder.basicAuthentication("admin", "123"));
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    private ResponseEntity<CarDTO> getCar(String url) {
        return rest.getForEntity(url, CarDTO.class);
    }

    private ResponseEntity<List<CarDTO>> getCars(String url) {
        return rest.withBasicAuth("admin", "123").exchange(
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
