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
  TagRepository repository;
  @Autowired
  UserRepository uRepo;

  @PostMapping("/api/users/{username}/tags")
  List<Tag> createTag(
    @PathVariable("username") String username,
    @RequestBody Tag newTag
  ) {
    User user = uRepo.findUser(username);
    newTag.setUser(user);
    repository.save(newTag);
    return repository.findAllTags();
  }

  @GetMapping("/api/users/{username}/tags")
  List<Tag> findTagsForUser(@PathVariable("username") String username) {
    return repository.findTagsForUser(username);
  }

  @GetMapping("/api/artists/{artistId}/tags")
  List<Tag> findTagsForArtist(@PathVariable("artistId") Integer artistId) {
    return repository.findTagsForArtist(artistId);
  }
}
