package com.codeclan.example.boneidleservice.components;

import com.codeclan.example.boneidleservice.models.Enemy;
import com.codeclan.example.boneidleservice.models.EnemyType;
import com.codeclan.example.boneidleservice.models.Player;
import com.codeclan.example.boneidleservice.models.forum.Post;
import com.codeclan.example.boneidleservice.models.forum.Thread;
import com.codeclan.example.boneidleservice.repositories.EnemyRepository;
import com.codeclan.example.boneidleservice.repositories.PlayerRepository;
import com.codeclan.example.boneidleservice.repositories.PostRepository;
import com.codeclan.example.boneidleservice.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    EnemyRepository enemyRepository;

    @Autowired
    ThreadRepository threadRepository;

    @Autowired
    PostRepository postRepository;

    public DataLoader(){

    }

    public void run(ApplicationArguments args){
        Player player1 = new Player("shinji_ikari", "I hate you, dad.", 1, false);
        Player player2 = new Player("gendo_ikari", "I hate you, son.", 25, true);
        Player player3 = new Player("asuka_langley", "I hate you all.", 14, false);
        Player player4 = new Player("misato_kusanagi", "I love beer.", 69, true);
        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);

        Enemy enemy1 = new Enemy("Marge", EnemyType.BOSS, 100);
        enemyRepository.save(enemy1);

        Thread thread1 = new Thread("Your Waifu is Trash.", player1);
        Thread thread2 = new Thread("Why Won't My Son Just Get In The Robot???", player2);
        threadRepository.save(thread1);
        threadRepository.save(thread2);

        Post post1 = new Post(player1, "Die mad about it, nerds.", thread1);
        Post post2 = new Post(player2, "Just get in the damn robot, idiot.", thread1);
        Post post3 = new Post(player4, "Shinji go to bed, it's 8pm.", thread1);
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        Post post4 = new Post(player2, "I can't even, anymore.", thread2);
        Post post5 = new Post(player1, "I wish you were dead.", thread2);
        Post post6 = new Post(player2, "I wish you would get in the damn robot.", thread2);
        postRepository.save(post4);
        postRepository.save(post5);
        postRepository.save(post6);

    }
}
