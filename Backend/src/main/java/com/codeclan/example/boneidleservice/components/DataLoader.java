package com.codeclan.example.boneidleservice.components;

import com.codeclan.example.boneidleservice.models.Enemy;
import com.codeclan.example.boneidleservice.models.EnemyType;
import com.codeclan.example.boneidleservice.models.User;
import com.codeclan.example.boneidleservice.models.forum.Post;
import com.codeclan.example.boneidleservice.models.forum.Thread;
import com.codeclan.example.boneidleservice.repositories.EnemyRepository;
import com.codeclan.example.boneidleservice.repositories.UserRepository;
import com.codeclan.example.boneidleservice.repositories.PostRepository;
import com.codeclan.example.boneidleservice.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EnemyRepository enemyRepository;

    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    PostRepository postRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){
        User user1 = new User("shinji_ikari", "I hate you, dad.", false);
        User user2 = new User("gendo_ikari", "I hate you, son.", true);
        User user3 = new User("asuka_langley", "I hate you all.", false);
        User user4 = new User("misato_kusanagi", "I love beer.", true);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        Enemy boss1 = new Enemy("Death", EnemyType.BOSS, 100, 100, "Lord of the underworld, the original goth.");
        Enemy boss2 = new Enemy("Sadako", EnemyType.BOSS, 100, 100, "Fell down a well, she'll send you to hell.");
        Enemy boss3 = new Enemy("Hollow", EnemyType.BOSS, 100, 100, "An empty vessel, motivated by hate.");
        Enemy boss4 = new Enemy("Emil", EnemyType.BOSS, 100, 100, "Number one best boy, no contest.");
        Enemy boss5 = new Enemy("Dio", EnemyType.BOSS, 100, 100, "Kono Dio da, Za Warudo, how many breads, wryyyy etc.");
        Enemy enemy1 = new Enemy("Slime", EnemyType.MONSTER, 100, 100, "Every game needs a slime.");
        Enemy enemy2 = new Enemy("Skeleton", EnemyType.MONSTER, 20, 20, "Some say they used to be adventurers, themselves.");
        Enemy enemy3 = new Enemy("Doll", EnemyType.MONSTER, 30, 30, "Every house needs one.");
        Enemy enemy4 = new Enemy("Mushaboom", EnemyType.MONSTER, 40, 40, "Caught you mirin', brah.");
        Enemy enemy5 = new Enemy("Shambler", EnemyType.MONSTER, 50, 50, "Not much of a conversationalist.");
        Enemy enemy6 = new Enemy("Tonberry", EnemyType.MONSTER, 60, 60, "Don't get close, that knife's sharper than it looks.");
        Enemy enemy7 = new Enemy("Unborn", EnemyType.MONSTER, 70, 70, "Searching for a mother, probably wise not to make eye contact.");
        Enemy enemy8 = new Enemy("Oni", EnemyType.MONSTER, 80, 80, "Is that a face or a mask? It's all very philosophical.");
        Enemy enemy9 = new Enemy("Screamer", EnemyType.MONSTER, 50, 50, "AHHHHHHHHHHHHHHHH.");
        Enemy enemy10 = new Enemy("Mimic", EnemyType.MONSTER, 90, 90, "Some say it can be bargained with, but few would pay the price.");
        enemyRepository.save(boss1);
        enemyRepository.save(boss2);
        enemyRepository.save(boss3);
        enemyRepository.save(boss4);
        enemyRepository.save(boss5);
        enemyRepository.save(enemy1);
        enemyRepository.save(enemy2);
        enemyRepository.save(enemy3);
        enemyRepository.save(enemy4);
        enemyRepository.save(enemy5);
        enemyRepository.save(enemy6);
        enemyRepository.save(enemy7);
        enemyRepository.save(enemy8);
        enemyRepository.save(enemy9);
        enemyRepository.save(enemy10);

        Thread thread1 = new Thread("Your Waifu is Trash.", user1);
        Thread thread2 = new Thread("Why Won't My Son Just Get In The Robot???", user2);
        threadRepository.save(thread1);
        threadRepository.save(thread2);

        Post post1 = new Post(user1, "Die mad about it, nerds.", thread1);
        Post post2 = new Post(user2, "Just get in the damn robot, idiot.", thread1);
        Post post3 = new Post(user4, "Shinji go to bed, it's 8pm.", thread1);
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        Post post4 = new Post(user2, "I can't even, anymore.", thread2);
        Post post5 = new Post(user1, "I wish you were dead.", thread2);
        Post post6 = new Post(user2, "I wish you would get in the damn robot.", thread2);
        postRepository.save(post4);
        postRepository.save(post5);
        postRepository.save(post6);

    }
}
