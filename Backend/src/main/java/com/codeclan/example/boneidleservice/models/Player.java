package com.codeclan.example.boneidleservice.models;


import com.codeclan.example.boneidleservice.models.forum.Post;
import com.codeclan.example.boneidleservice.models.forum.Thread;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String username;
    @Column(name="tag")
    private String tag;
    @Column(name="level")
    private int level;
    @Column(name="admin")
    private Boolean admin;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Thread> threads;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Post> posts;

    public Player(){

    }

    public Player(String username, String tag, int level, Boolean admin) {
        this.username = username;
        this.tag = tag;
        this.level = level;
        this.admin = admin;
        this.threads = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = username;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
