package com.codeclan.example.boneidleservice.repositories;

import com.codeclan.example.boneidleservice.models.Enemy;
import com.codeclan.example.boneidleservice.models.EnemyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnemyRepository extends JpaRepository<Enemy, Long> {

    List<Enemy> findByType(EnemyType type);

}
