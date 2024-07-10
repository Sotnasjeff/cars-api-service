package com.example.cars.api.service.domain.dto;

import com.example.cars.api.service.domain.entity.Car;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarDTO {
    private Long id;

    private String name;
    private String type;

    public static CarDTO create(Car c){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarDTO.class);
    }
}
