package com.example.webdevf19t16backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Review {
  @Id
  private String id;
  @ManyToOne
  @JsonIgnore
  private User user;
  @Lob
  @Column(name="text", length=1000)
  private String text;
  private Integer songId;

  public Review() {
    this.id = UUID.randomUUID().toString();
    this.text = "";
  }

  public Integer getSongId() {
    return songId;
  }

  public void setSongId(Integer songId) {
    this.songId = songId;
  }

  public Review(String text, Integer songId) {
    this.id = UUID.randomUUID().toString();
    this.text = text;
    this.songId = songId;
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