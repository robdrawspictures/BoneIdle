package com.codeclan.example.boneidleservice.models.forum;

import com.codeclan.example.boneidleservice.models.Player;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="threads")
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @ManyToOne
    @JoinColumn(name="creator")
    private Player creator;
    @OneToMany(mappedBy = "thread", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Post> posts;
    @Column(name="locked")
    private Boolean locked;

    public Thread(){

    }

    public Thread(String title, Player creator) {
        this.title = title;
        this.creator = creator;
        this.posts = new ArrayList<>();
        this.locked = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Player getCreator() {
        return creator;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
