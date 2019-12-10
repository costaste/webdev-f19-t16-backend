package com.example.webdevf19t16backend.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {
  @Id
  private String id;

  @ManyToOne
  @JsonIgnore
  private User user;
  private String text;
  private Integer artistId;

  public Tag() {
    this.id = UUID.randomUUID().toString();
    this.text = "";
  }

  public Tag(String text, Integer artistId) {
    this.id = UUID.randomUUID().toString();
    this.text = text;
    this.artistId = artistId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getText() {
    return text;
  }

  public Integer getArtistId() {
    return artistId;
  }

  public void setArtistId(Integer artistId) {
    this.artistId = artistId;
  }

  public void setText(String text) {
    this.text = text;
  }


}
