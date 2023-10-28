package com.test.microservice.services;

import com.test.microservice.dtos.CreateUserDto;
import com.test.microservice.dtos.UpdateUserDto;
import com.test.microservice.models.User;
import com.test.microservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(Long id, UpdateUserDto user) {
        return userRepository.findById(id).map(foundUser -> {
            foundUser.setName(user.getName());
            foundUser.setAge(user.getAge());
            foundUser.setEmail(user.getEmail());
            return userRepository.save(foundUser);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
