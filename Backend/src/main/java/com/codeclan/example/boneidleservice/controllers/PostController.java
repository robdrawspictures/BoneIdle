package com.codeclan.example.boneidleservice.controllers;

import com.codeclan.example.boneidleservice.repositories.UserRepository;
import com.codeclan.example.boneidleservice.repositories.PostRepository;
import com.codeclan.example.boneidleservice.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    UserRepository playerRepository;

    @GetMapping(value="/posts")
    public ResponseEntity getPosts(
            @RequestParam(name="user", required = false) String name,
            @RequestParam(name="thread", required = false) String title,
            @RequestParam(name="thread_id", required = false) Long threadId){
        if(name != null){
            return new ResponseEntity(postRepository.findByUserUsername(name), HttpStatus.OK);
        } else if(title != null){
            return new ResponseEntity(postRepository.findByThreadTitle(title), HttpStatus.OK);
        } else if(threadId != null){
            return new ResponseEntity(postRepository.findByThreadId(threadId), HttpStatus.OK);
        }
            return new ResponseEntity(postRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/posts/{id}")
    public ResponseEntity getPost(@PathVariable Long id){
        return new ResponseEntity(postRepository.findById(id), HttpStatus.OK);
    }
}
