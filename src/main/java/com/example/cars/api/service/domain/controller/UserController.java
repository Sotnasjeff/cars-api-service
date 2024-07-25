package com.example.cars.api.service.domain.controller;

import com.example.cars.api.service.domain.dto.CarDTO;
import com.example.cars.api.service.domain.dto.UserDTO;
import com.example.cars.api.service.domain.entity.User;
import com.example.cars.api.service.domain.service.UserService;
import com.example.cars.api.service.domain.utils.UriCatcher;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UriCatcher uriCatcher;

    @GetMapping("/info")
    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.insertUser(user);

        URI location = uriCatcher.getUri(newUser.getId());

        return ResponseEntity.created(location).build();
    }

}
