package com.example.cars.api.service.domain.dto;

import com.example.cars.api.service.domain.entity.Car;
import com.example.cars.api.service.domain.entity.Role;
import com.example.cars.api.service.domain.entity.User;
import java.util.List;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDTO {
    private Long id;

    private String name;
    private String login;
    private String password;
    private List<Role> roles;

    public static UserDTO create(User c){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, UserDTO.class);
    }
}
