package com.codeclan.example.boneidleservice.repositories;

import com.codeclan.example.boneidleservice.models.forum.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {


}
