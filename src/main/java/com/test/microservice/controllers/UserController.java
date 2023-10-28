package com.test.microservice.controllers;

import com.test.microservice.dtos.CreateUserDto;
import com.test.microservice.dtos.UpdateUserDto;
import com.test.microservice.models.User;
import com.test.microservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserDto userDto) {
        User createdUser = userService.createUser(userDto);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") Long id) {
        Optional<User> foundUser = userService.getUser(id);

        if (foundUser.isEmpty()) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }

        return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long id, @RequestBody @Valid UpdateUserDto userDto) {
        Optional<User> updatedUser = userService.updateUser(id, userDto);

        if (updatedUser.isEmpty()) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }

        return new ResponseEntity<>(updatedUser.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable("userId") Long id) {
        Optional<User> foundUser = userService.getUser(id);

        if (foundUser.isEmpty()) {
            throw new NoSuchElementException("User with id " + id + " not found");
        }

        userService.deleteUser(id);

        return new ResponseEntity<>(foundUser.get(), HttpStatus.OK);
    }
}
