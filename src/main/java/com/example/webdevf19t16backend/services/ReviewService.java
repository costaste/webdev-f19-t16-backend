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
public class ReviewService {

  @Autowired
  ReviewRepository repository;
  @Autowired
  UserRepository uRepo;

  @PostMapping("/api/users/{username}/reviews")
  List<Review> createReview(
    @PathVariable("username") String username,
    @RequestBody Review newReview
  ) {
    User user = uRepo.findUser(username);
    newReview.setUser(user);
    repository.save(newReview);
    return repository.findAllReviews();
  }

  @GetMapping("/api/users/{username}/reviews")
  List<Review> findReviewsForUser(@PathVariable("username") String username) {
    return repository.findReviewsForUser(username);
  }
}
