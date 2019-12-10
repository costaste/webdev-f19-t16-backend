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
  private final String userUrl = "/api/users";

  @Autowired
  UserRepository userRepo;
  @Autowired
  ReviewRepository reviewRepo;

  @GetMapping(userUrl + "/{username}")
  User getUser(
          @PathVariable("username") String username,
          @RequestBody String password) {
    User user = userRepo.findUserFromUsername(username);

    if(password.equals(user.getPassword())) {
      return user;
    } else {
      return new User(user.getUsername(), "", user.getRole(), user.getPhotoUrl());
    }
  }

  // Creates a new User instance and add it to the existing collection of Users.
  @PostMapping(userUrl)
  User createUser(@RequestBody User newUser) {
    userRepo.save(newUser);
    return userRepo.findUserFromUsername(newUser.getUsername());
  }

  // Method to let frontend know if a user with that username already exists
  // (false = invalid, true = valid)
  @PostMapping(userUrl + "/validate")
  Boolean validateNewUser(@RequestBody User newUser) {
    return !newUser.getUsername().equals("")
            && !newUser.getPassword().equals("")
            && userRepo.findUserFromUsername(newUser.getUsername())==null;
  }

  // Checks that user credentials exist
  @PostMapping(userUrl + "/login")
  User loginUser(@RequestBody User user) {
    User logInUser = userRepo.findUserFromUsername(user.getUsername());
    return logInUser != null && logInUser.getPassword().equals(user.getPassword()) ?
            logInUser :
            new User("", "", "", "");
  }

  // add review "like" from this user
  @PutMapping(userUrl + "/{username}/reviews/{reviewId}")
  List<Review> likeReview(
          @PathVariable("username") String username,
          @PathVariable("reviewId") String reviewId) {
    User user = userRepo.findUserFromUsername(username);
    Review review = reviewRepo.findReviewFromId(reviewId);

    user.addLike(review);
    review.addLike(user);
    userRepo.save(user);
    reviewRepo.save(review);

    return reviewRepo.findAllReviews();
  }

  // edit review by this user
  @PutMapping(userUrl + "/{username}/reviews")
  List<Review> editReview(
          @PathVariable("username") String username,
          @RequestBody Review editedReview) {
    Review review = reviewRepo.findReviewFromId(editedReview.getId());
    review.setText(editedReview.getText());
    reviewRepo.save(review);

    return reviewRepo.findAllReviews();
  }
}
