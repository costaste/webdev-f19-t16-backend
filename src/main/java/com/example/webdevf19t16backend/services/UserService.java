package com.example.webdevf19t16backend.services;

import com.example.webdevf19t16backend.models.User;
import com.example.webdevf19t16backend.repositories.UserRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin(origins="*",allowCredentials="true",allowedHeaders="*")
public class UserService {

  @Autowired
  UserRepository repository;

  // Creates a new User instance and add it to the existing collection of Users.
  @PostMapping("/api/users")
  List<User> createUser(@RequestBody User newUser) {
    repository.save(newUser);
    return repository.findAllUsers();
  }

  // Method to let frontend know if a user with that username already exists
  @PostMapping("/api/users/validate")
  Boolean validateUser(@RequestBody User newUser) {
    List<User> users = repository.findAllUsers();
    for (User user : users) {
        if (user.getUsername().equals(newUser.getUsername())) {
            return false;
        }
    }
    return true;
  }

  // Checks that user credentials exist
  @PostMapping("/api/users/login")
  Boolean loginUser(@RequestBody User user) {
    List<User> users = repository.findAllUsers();
    for (User u : users) {
        if (u.getUsername() == user.getUsername() && u.getPassword() == user.getPassword()) {
            return true;
        }
    }
    return false;
  }
}