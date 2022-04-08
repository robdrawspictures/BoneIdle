package com.codeclan.example.boneidleservice.repositories;

import com.codeclan.example.boneidleservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);
    List<User> findByLevel(int level);
    List<User> findByPostsGreaterThan(int num);

}
