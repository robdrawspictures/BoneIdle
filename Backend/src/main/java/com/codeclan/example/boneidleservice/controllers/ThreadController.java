package com.codeclan.example.boneidleservice.controllers;

import com.codeclan.example.boneidleservice.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;

    @GetMapping(value="/threads")
    public ResponseEntity getThreads(
            @RequestParam(name="creator", required = false) String username){
        if(username != null){
            return new ResponseEntity(threadRepository.findByCreatorUsername(username), HttpStatus.OK);
        } return new ResponseEntity(threadRepository.findAll(), HttpStatus.OK);
    }

}
