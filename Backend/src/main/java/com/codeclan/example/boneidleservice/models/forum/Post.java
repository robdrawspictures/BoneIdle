package com.codeclan.example.boneidleservice.models.forum;

import com.codeclan.example.boneidleservice.models.User;

import javax.persistence.*;


@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="player", nullable = false)
    private User user;
    @Column(name="body")
    private String body;
    @ManyToOne
    @JoinColumn(name="thread", nullable = false)
    private Thread thread;

    public Post(){

    }

    public Post(User user, String body, Thread thread) {
        this.user = user;
        this.body = body;
        this.thread = thread;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
