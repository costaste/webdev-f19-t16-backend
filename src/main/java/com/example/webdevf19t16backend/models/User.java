package com.example.webdevf19t16backend.models;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
  @Id
  private String username;
  private String password;
  private String role;
  private String photoUrl;

  @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<Review> reviews;

  @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<Review> tags;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
    name = "review_likes",
    joinColumns = {@JoinColumn(name = "user_username")},
    inverseJoinColumns = {@JoinColumn(name = "review_id")}
  )
  private Set<Review> likedReviews;

  public User() {
    this.username = UUID.randomUUID().toString();;
    this.password = "password";
    this.role = "reviewer";
    this.photoUrl = "";
  }

  public User(String username, String password, String role, String photoUrl) {
    this.username = username==null ? UUID.randomUUID().toString() : username;
    this.password = password;
    this.role = role;
    this.photoUrl = photoUrl;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getRole() {
    return role;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void addLike(Review r) {
    this.likedReviews.add(r);
    r.getLikes().add(this);
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }
}
