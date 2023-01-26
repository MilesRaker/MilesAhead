package com.milesraker.milesahead.repositories;

import com.milesraker.milesahead.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
    Post findFirstByTitle(String title);
    Post findById(long id);

    List<Post> findAll();
}
