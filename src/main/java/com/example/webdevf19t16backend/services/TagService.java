package com.example.webdevf19t16backend.services;

import com.example.webdevf19t16backend.models.Tag;
import com.example.webdevf19t16backend.models.User;
import com.example.webdevf19t16backend.repositories.TagRepository;
import com.example.webdevf19t16backend.repositories.UserRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin(origins="*",allowCredentials="true",allowedHeaders="*")
public class TagService {

  @Autowired
  TagRepository tagRepo;
  @Autowired
  UserRepository userRepo;

  @PostMapping("/api/users/{username}/tags")
  List<Tag> createTag(
    @PathVariable("username") String username,
    @RequestBody Tag newTag) {
    User user = userRepo.findUserFromUsername(username);
    newTag.setUser(user);
    tagRepo.save(newTag);
    return tagRepo.findAllTags();
  }

  @GetMapping("/api/users/{username}/tags")
  List<Tag> findTagsForUser(@PathVariable("username") String username) {
    return tagRepo.findTagsForUser(username);
  }

  @GetMapping("/api/artists/{artistId}/tags")
  List<Tag> findTagsForArtist(@PathVariable("artistId") Integer artistId) {
    return tagRepo.findTagsForArtist(artistId);
  }
}
