package com.codeclan.example.boneidleservice.repositories;

import com.codeclan.example.boneidleservice.models.forum.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    List<Thread> findByCreatorUsername(String name);


}
