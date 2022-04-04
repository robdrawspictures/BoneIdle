package com.codeclan.example.boneidleservice.repositories;

import com.codeclan.example.boneidleservice.models.forum.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserUsername(String name);
    List<Post> findByThreadTitle(String title);
}
