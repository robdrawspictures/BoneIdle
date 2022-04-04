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
    private int HP;

    public Enemy(){

    }

    public Enemy(String name, EnemyType type, int HP) {
        this.name = name;
        this.type = type;
        this.HP = HP;
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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
