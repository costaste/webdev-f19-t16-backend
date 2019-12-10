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
  ReviewRepository reviewRepo;
  @Autowired
  UserRepository userRepo;

  @PostMapping("/api/users/{username}/reviews")
  List<Review> createReview(
    @PathVariable("username") String username,
    @RequestBody Review newReview) {
    User user = userRepo.findUserFromUsername(username);
    newReview.setUser(user);
    reviewRepo.save(newReview);
    return reviewRepo.findAllReviews();
  }

  @GetMapping("/api/users/{username}/reviews")
  List<Review> findReviewsForUser(@PathVariable("username") String username) {
    return reviewRepo.findReviewsForUser(username);
  }

  @GetMapping("/api/songs/{songId}/reviews")
  List<Review> findReviewsForSong(@PathVariable("songId") Integer songId) {
    return reviewRepo.findReviewsForSong(songId);
  }

  @DeleteMapping("/api/reviews/{reviewId}")
  List<Review> deleteReview(@PathVariable("reviewId") String reviewId) {
    Review review = reviewRepo.findReviewFromId(reviewId);
    reviewRepo.delete(review);
    return reviewRepo.findAllReviews();
  }
}
