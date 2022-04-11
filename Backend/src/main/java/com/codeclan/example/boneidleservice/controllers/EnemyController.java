package com.codeclan.example.boneidleservice.controllers;

import com.codeclan.example.boneidleservice.models.Enemy;
import com.codeclan.example.boneidleservice.models.EnemyType;
import com.codeclan.example.boneidleservice.repositories.EnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
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

    @PostMapping(value="/enemies")
    public ResponseEntity<Enemy> postEnemy(@RequestBody Enemy enemy){
        enemyRepository.save(enemy);
        return new ResponseEntity<>(enemy, HttpStatus.CREATED);
    }

//    @PatchMapping(value="/enemies/{id}/{fieldToUpdate}")
//    public ResponseEntity<Enemy> updateEnemy(@PathVariable Long id, @PathVariable String fieldToUpdate){
//        Enemy enemyToUpdate = enemyRepository.findById(id).get();
//
//        return new ResponseEntity<>(enemyToUpdate, HttpStatus.OK);
//    }

    @PatchMapping("/enemies/{id}")
    public ResponseEntity<Enemy> updateEnemyBio(@RequestBody Enemy enemy) {
        enemyRepository.save(enemy);
        return new ResponseEntity<>(enemy, HttpStatus.OK);
    }

//    @PatchMapping("/enemies/{id}")
//    public ResponseEntity<?> updateEnemy(
//            @RequestBody Map<String, Object> updates,
//            @PathVariable Long id) {
//
//        enemyRepository.save(updates, id)
//        return ResponseEntity.ok(enemyRepository.findById(id));
//    }


    @DeleteMapping(value="/enemies/{id}")
    public ResponseEntity deleteEnemy(@PathVariable Long id){
        enemyRepository.deleteById(id);
        return new ResponseEntity<>(enemyRepository.findAll(), HttpStatus.OK);
    }
}
