package com.example.webdevf19t16backend.repositories;

import com.example.webdevf19t16backend.models.Review;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, String> {
  @Query("SELECT review from Review review WHERE review.id=:rid")
  public Review findReview(@Param("rid") String rid);

  @Query("SELECT review from Review review")
  public List<Review> findAllReviews();

  @Query("SELECT review from Review review WHERE review.user.username=:username")
  public List<Review> findReviewsForUser(@Param("username") String username);
}
