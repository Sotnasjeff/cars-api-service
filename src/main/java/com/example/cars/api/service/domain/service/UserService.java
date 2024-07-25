package com.example.cars.api.service.domain.service;

import com.example.cars.api.service.domain.dto.CarDTO;
import com.example.cars.api.service.domain.dto.UserDTO;
import com.example.cars.api.service.domain.entity.User;
import com.example.cars.api.service.domain.exception.CarNotFoundException;
import com.example.cars.api.service.domain.exception.UserNotFoundException;
import com.example.cars.api.service.domain.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User insertUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return repository.save(user);
    }

    public List<User> getUser() {
        return repository.findAll();
    }

    public UserDTO getUserById(Long id) {
        return repository.findById(id).map(UserDTO::create).orElseThrow(() -> new UserNotFoundException("User has not been found by id:" + id));    }
}
