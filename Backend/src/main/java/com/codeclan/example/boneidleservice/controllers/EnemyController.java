package com.codeclan.example.boneidleservice.controllers;

import com.codeclan.example.boneidleservice.models.EnemyType;
import com.codeclan.example.boneidleservice.repositories.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnemyController {

    @Autowired
    EnemyRepository enemyRepository;

    @GetMapping(value="/enemies")
    public ResponseEntity getEnemies(
            @RequestParam(name="type", required = false) EnemyType enemy){
        if(enemy != null){
            return new ResponseEntity(enemyRepository.findByType(enemy), HttpStatus.OK);
        } return new ResponseEntity(enemyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/enemies/{id}")
    public ResponseEntity getEnemyByID(@PathVariable Long id){
        return new ResponseEntity(enemyRepository.findById(id), HttpStatus.OK);
    }
}
