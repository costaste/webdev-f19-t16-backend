package com.example.webdevf19t16backend.repositories;

import com.example.webdevf19t16backend.models.Tag;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, String> {
  @Query("SELECT tag from Tag tag WHERE tag.id=:tid")
  public Tag findTag(@Param("tid") String tid);

  @Query("SELECT tag from Tag tag")
  public List<Tag> findAllTags();

  @Query("SELECT tag from Tag tag WHERE tag.user.username=:username")
  public List<Tag> findTagsForUser(@Param("username") String username);

  @Query("SELECT tag from Tag tag WHERE tag.artistId=:artistId")
  public List<Tag> findTagsForArtist(@Param("artistId") Integer artistId);
}
