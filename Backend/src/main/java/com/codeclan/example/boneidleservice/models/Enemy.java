package com.codeclan.example.boneidleservice.models;

import javax.persistence.*;

@Entity
@Table(name="enemies")
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="type")
    private EnemyType type;
    @Column(name="hp")
    private int hp;
    @Column(name="maxHP")
    private int maxHP;
    @Column(name="bio")
    private String bio;

    public Enemy(){

    }

    public Enemy(String name, EnemyType type, int hp, int maxHP, String bio) {
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.maxHP = maxHP;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
