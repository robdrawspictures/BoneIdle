package com.codeclan.example.boneidleservice.repositories;

import com.codeclan.example.boneidleservice.models.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnemyRepository extends JpaRepository<Enemy, Long> {
}
