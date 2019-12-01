package com.example.webdevf19t16backend.repositories;

import com.example.webdevf19t16backend.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
  @Query("SELECT user from User user WHERE user.username=:username")
  public User findUser(@Param("username") String username);

  @Query("SELECT user from User user")
  public List<User> findAllUsers();
}