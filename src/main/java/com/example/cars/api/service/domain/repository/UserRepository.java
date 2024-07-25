package com.example.cars.api.service.domain.repository;

import com.example.cars.api.service.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
