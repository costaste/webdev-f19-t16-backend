package com.example.webdevf19t16backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="reviews")
public class Review {
  @Id
  private String id;
  @ManyToOne
  @JsonIgnore
  private User user;
  private String text;

  public Review() {
    this.id = UUID.randomUUID().toString();
    this.text = "";
  }

  public Review(User user) {
    this.id = UUID.randomUUID().toString();
    this.text = "";
    this.user = user;
  }

  public String getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public String getText() {
    return text;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setText(String text) {
    this.text = text;
  }
}