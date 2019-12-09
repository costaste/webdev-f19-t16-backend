package com.example.webdevf19t16backend.services;

import com.example.webdevf19t16backend.models.Review;
import com.example.webdevf19t16backend.models.User;
import com.example.webdevf19t16backend.repositories.ReviewRepository;
import com.example.webdevf19t16backend.repositories.UserRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin(origins="*",allowCredentials="true",allowedHeaders="*")
public class UserService {

  @Autowired
  UserRepository repository;
  @Autowired
  ReviewRepository revRepo;

  // Creates a new User instance and add it to the existing collection of Users.
  @PostMapping("/api/users")
  User createUser(@RequestBody User newUser) {
    repository.save(newUser);
    return repository.findUser(newUser.getUsername());
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
        if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
            return true;
        }
    }
    return false;
  }

  @PutMapping("/api/users/{username}/reviews/{reviewId}")
  List<Review> likeReview(
    @PathVariable("username") String username,
    @PathVariable("reviewId") String reviewId
  ) {
    User user = repository.findUser(username);
    Review review = revRepo.findReview(reviewId);
    System.out.println("USERNAME::" + username);
    System.out.println("REVIEWID::" + reviewId);
    user.addLike(review);
    review.addLike(user);
    repository.save(user);
    revRepo.save(review);
    return revRepo.findAllReviews();
  }
}