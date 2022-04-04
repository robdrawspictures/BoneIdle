package com.codeclan.example.boneidleservice.repositories;

import com.codeclan.example.boneidleservice.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
