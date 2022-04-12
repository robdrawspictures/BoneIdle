package com.codeclan.example.boneidleservice.models;


import com.codeclan.example.boneidleservice.models.forum.Post;
import com.codeclan.example.boneidleservice.models.forum.Thread;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="players")
public class User {

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
    @Column(name="gold")
    private int gold;
    @Column(name="kills")
    private int kills;
    @Column(name="radiant")
    private int radiant;
    @Column(name="banned")
    private Boolean banned;
    @Column(name="admin")
    private Boolean admin;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    @JsonBackReference(value="user-threads")
    private List<Thread> threads;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference(value="user-posts")
    private List<Post> posts;

    public User(String username, String tag, Boolean admin) {
        this.username = username;
        this.tag = tag;
        this.level = 1;
        this.gold = 0;
        this.kills = 0;
        this.radiant = 0;
        this.banned = false;
        this.admin = admin;
        this.threads = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public User(){
        this.level = 1;
        this.banned = false;
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

    public void setUsername(String username) {
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

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getRadiant() {
        return radiant;
    }

    public void setRadiant(int radiant) {
        this.radiant = radiant;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
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

    public int getThreadCount(){
        return this.threads.size();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getPostCount(){
        return this.posts.size();
    }

}
