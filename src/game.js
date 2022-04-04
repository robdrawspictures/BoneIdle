let game = new Phaser.Game(800, 600, Phaser.AUTO, '');

game.state.add('play', {

    preload: function() {
        this.game.load.image('forest-back', 'assets/parallax_forest_pack/layers/parallax-forest-back-trees.png');
        this.game.load.image('forest-lights', 'assets/parallax_forest_pack/layers/parallax-forest-lights.png');
        this.game.load.image('forest-middle', 'assets/parallax_forest_pack/layers/parallax-forest-middle-trees.png');
        this.game.load.image('forest-front', 'assets/parallax_forest_pack/layers/parallax-forest-front-trees.png');

        this.game.load.image('aerocephal', 'assets/allacrost_enemy_sprites/aerocephal.png');
        this.game.load.image('arcana_drake', 'assets/allacrost_enemy_sprites/arcana_drake.png');
        this.game.load.image('aurum-drakueli', 'assets/allacrost_enemy_sprites/aurum-drakueli.png');
        this.game.load.image('bat', 'assets/allacrost_enemy_sprites/bat.png');
        this.game.load.image('daemarbora', 'assets/allacrost_enemy_sprites/daemarbora.png');
        this.game.load.image('deceleon', 'assets/allacrost_enemy_sprites/deceleon.png');
        this.game.load.image('demonic_essence', 'assets/allacrost_enemy_sprites/demonic_essence.png');
        this.game.load.image('dune_crawler', 'assets/allacrost_enemy_sprites/dune_crawler.png');
        this.game.load.image('green_slime', 'assets/allacrost_enemy_sprites/green_slime.png');
        this.game.load.image('nagaruda', 'assets/allacrost_enemy_sprites/nagaruda.png');
        this.game.load.image('rat', 'assets/allacrost_enemy_sprites/rat.png');
        this.game.load.image('scorpion', 'assets/allacrost_enemy_sprites/scorpion.png');
        this.game.load.image('skeleton', 'assets/allacrost_enemy_sprites/skeleton.png');
        this.game.load.image('snake', 'assets/allacrost_enemy_sprites/snake.png');
        this.game.load.image('spider', 'assets/allacrost_enemy_sprites/spider.png');
        this.game.load.image('stygian_lizard', 'assets/allacrost_enemy_sprites/stygian_lizard.png');

        this.game.load.image('skull', 'assets/skull.png');

        this.game.load.image('dagger', 'assets/496_RPG_icons/W_Dagger002.png');
        this.game.load.image('swordIcon1', 'assets/496_RPG_icons/S_Sword15.png');

        // build panel for upgrades
        let bmd = this.game.add.bitmapData(300, 600);
        bmd.ctx.fillStyle = 'black';
        bmd.ctx.strokeStyle = 'red';
        bmd.ctx.lineWidth = 12;
        bmd.ctx.fillRect(0, 0, 300, 600);
        bmd.ctx.strokeRect(0, 0, 300, 600);
        this.game.cache.addBitmapData('upgradePanel', bmd);

        let buttonImage = this.game.add.bitmapData(476, 48);
        buttonImage.ctx.fillStyle = 'black';
        buttonImage.ctx.strokeStyle = 'white';
        buttonImage.ctx.lineWidth = 4;
        buttonImage.ctx.fillRect(0, 0, 225, 48);
        buttonImage.ctx.strokeRect(0, 0, 225, 48);
        this.game.cache.addBitmapData('button', buttonImage);

        let healthBar = this.game.add.bitmapData(110, 20);
        healthBar.ctx.fillStyle = 'black';
        healthBar.ctx.strokeStyle = 'white';
        healthBar.ctx.lineWidth = 5;
        healthBar.ctx.fillRect(0, 0, 110, 20);
        healthBar.ctx.strokeRect(0, 0, 110, 20);
        this.game.cache.addBitmapData('healthbar', healthBar);



        // the main player
        this.player = {
            // how much damage is done per click
            strength: 10,
            // gained from killing enemies to upgrade
            gold: 0,
            // damage done automatically
            dps: 0,
        };

        // player level
        this.level = 1;
        // current player XP
        this.currentXP = 0;
        this.killCount = 0;
        // how much XP to level up
        this.xpReq = 100;

        
    },
    create: function() {
        let state = this;

        // let keyObj = this.game.input.keyboard.addKey('space');
        // keyObj.on('down', onClickMonster());

        // this.background = this.game.add.group();
        // // setup each of our background layers to take the full screen
        // ['forest-back', 'forest-lights', 'forest-middle', 'forest-front']
        //     .forEach(function(image) {
        //         var bg = state.game.add.tileSprite(0, 0, state.game.world.width,
        //             state.game.world.height, image, '', state.background);
        //         bg.tileScale.setTo(4,4);
        //     });

        this.upgradePanel = this.game.add.image(500, 0, this.game.cache.getBitmapData('upgradePanel'));
        var upgradeButtons = this.upgradePanel.addChild(this.game.add.group());
        upgradeButtons.position.setTo(8, 8);

        var upgradeButtonsData = [
            {icon: 'dagger', name: 'Attack', level: 0, cost: 5, purchaseHandler: function(button, player) {
                player.strength += 1;
            }},
            {icon: 'swordIcon1', name: 'Auto-Attack', level: 0, cost: 25, purchaseHandler: function(button, player) {
                player.dps += 5;
            }}
        ];

        var button;
        upgradeButtonsData.forEach(function(buttonData, index) {
            button = state.game.add.button(0, (50 * index), state.game.cache.getBitmapData('button'));
            button.icon = button.addChild(state.game.add.image(6, 6, buttonData.icon));
            button.text = button.addChild(state.game.add.text(42, 6, buttonData.name + ': ' + buttonData.level, {font: '16px Courier New', fill: 'white'}));
            button.details = buttonData;
            button.costText = button.addChild(state.game.add.text(42, 24, 'Cost: ' + buttonData.cost, {font: '16px Courier New', fill: 'white'}));
            button.events.onInputDown.add(state.onUpgradeButtonClick, state);

            upgradeButtons.addChild(button);
        });

        let monsterData = [
            {name: 'Aerocephal',        image: 'aerocephal',        maxHealth: 10},
            {name: 'Bat',               image: 'bat',               maxHealth: 20},
            {name: 'Daemarbora',        image: 'daemarbora',        maxHealth: 30},
            {name: 'Deceleon',          image: 'deceleon',          maxHealth: 40},
            {name: 'Green Slime',       image: 'green_slime',       maxHealth: 30},
            {name: 'Nagaruda',          image: 'nagaruda',          maxHealth: 10},
            {name: 'Rat',               image: 'rat',               maxHealth: 20},
            {name: 'Scorpion',          image: 'scorpion',          maxHealth: 20},
            {name: 'Skeleton',          image: 'skeleton',          maxHealth: 60},
            {name: 'Snake',             image: 'snake',             maxHealth: 40},
            {name: 'Spider',            image: 'spider',            maxHealth: 40},
            
        ];
        this.monsters = this.game.add.group();

        let bossData = [
            {name: 'Stygian Lizard',    image: 'stygian_lizard',    maxHealth: 100},
            {name: 'Arcana Drake',      image: 'arcana_drake',      maxHealth: 100},
            {name: 'Aurum Drakueli',    image: 'aurum-drakueli',    maxHealth: 100},
            {name: 'Demonic Essence',   image: 'demonic_essence',   maxHealth: 100},
            {name: 'Dune Crawler',      image: 'dune_crawler',      maxHealth: 100}
        ];
        this.bosses = this.game.add.group();

        let monster;
        monsterData.forEach(function(data) {
            // create a sprite for them off screen
            monster = state.monsters.create(1000, state.game.world.centerY, data.image);
            // use the built in health component
            monster.health = monster.maxHealth = data.maxHealth;
            // center anchor
            monster.anchor.setTo(0.5, 1);
            // reference to the database
            monster.details = data;

            //enable input so we can click it!
            monster.inputEnabled = true;
            monster.events.onInputDown.add(state.onClickMonster, state);

            // hook into health and lifecycle events
            monster.events.onKilled.add(state.onKilledMonster, state);
            monster.events.onRevived.add(state.onRevivedMonster, state);
        });

        // display the monster front and center
        this.currentMonster = this.monsters.getRandom();
        this.currentMonster.position.set(this.game.world.centerX - 200, this.game.world.centerY + 100);

        this.monsterInfoUI = this.game.add.group();
        this.monsterInfoUI.position.setTo(this.currentMonster.x - 180, this.currentMonster.y + 70);
        this.monsterNameText = this.monsterInfoUI.addChild(this.game.add.text(0, 0, this.currentMonster.details.name, {
            font: '32px Courier New',
            fill: '#fff',
            strokeThickness: 4
        }));
        this.monsterHealthText = this.monsterInfoUI.addChild(this.game.add.text(0, 80, 'HP:' + this.currentMonster.health + '/' + this.currentMonster.maxHealth, {
            font: '32px Courier New',
            fill: '#ff0000',
            strokeThickness: 4
        }));

        this.dmgTextPool = this.add.group();
        let dmgText;
        for (var d=0; d<50; d++) {
            dmgText = this.add.text(0, 0, '1', {
                font: '64px Courier New',
                fill: '#fff',
                strokeThickness: 4
            });
            // start out not existing, so we don't draw it yet
            dmgText.exists = false;
            dmgText.tween = game.add.tween(dmgText)
                .to({
                    alpha: 0,
                    y: 100,
                    x: this.game.rnd.integerInRange(100, 700)
                }, 1000, Phaser.Easing.Cubic.Out);

            dmgText.tween.onComplete.add(function(text, tween) {
                text.kill();
            });
            this.dmgTextPool.add(dmgText);
        }

        // let boss;
        // bossData.forEach(function(data) {
        //     // create a sprite for them off screen
        //     boss = state.bosses.create(1000, state.game.world.centerY, data.image);
        //     // use the built in health component
        //     boss.health = boss.maxHealth = data.maxHealth;
        //     // center anchor
        //     boss.anchor.setTo(0.5, 1);
        //     // reference to the database
        //     boss.details = data;

        //     //enable input so we can click it!
        //     boss.inputEnabled = true;
        //     boss.events.onInputDown.add(state.onClickBoss, state);

        //     // hook into health and lifecycle events
        //     boss.events.onKilled.add(state.onKilledBoss, state);
        //     boss.events.onRevived.add(state.onRevivedBoss, state);
        // });

        // // display the monster front and center
        // this.currentBoss = this.bosses.getRandom();
        // this.currentBoss.position.set(this.game.world.centerX - 200, this.game.world.centerY + 100);

        // this.bossInfoUI = this.game.add.group();
        // this.bossInfoUI.position.setTo(this.currentBoss.x - 180, this.currentBoss.y + 70);
        // this.bossNameText = this.bossInfoUI.addChild(this.game.add.text(0, 0, "BOSS: " + this.currentBoss.details.name, {
        //     font: '32px Courier New',
        //     fill: '#fff',
        //     strokeThickness: 4
        // }));
        // this.bossHealthText = this.bossInfoUI.addChild(this.game.add.text(0, 80, 'HP:' + this.currentBoss.health + '/' + this.currentBoss.maxHealth, {
        //     font: '32px Courier New',
        //     fill: '#ff0000',
        //     strokeThickness: 4
        // }));
        
        // create a pool of gold coins
        this.coins = this.add.group();
        this.coins.createMultiple(50, 'skull', '', false);
        this.coins.setAll('inputEnabled', true);
        this.coins.setAll('goldValue', 1);
        this.coins.callAll('events.onInputDown.add', 'events.onInputDown', this.onClickCoin, this);

        this.playerGoldText = this.add.text(510, 500, 'Gold: ' + this.player.gold, {
            font: '24px Courier New',
            fill: '#fff',
            strokeThickness: 4
        });

        // 100ms 10x a second
        this.dpsTimer = this.game.time.events.loop(100, this.onDPS, this);

        // setup the world progression display
        this.levelUI = this.game.add.group();
        this.levelUI.position.setTo(510, 530);
        this.levelText = this.levelUI.addChild(this.game.add.text(0, 0, 'LV.' + this.level, {
            font: '24px Courier New',
            fill: '#fff',
            strokeThickness: 4
        }));
        this.levelKillsText = this.levelUI.addChild(this.game.add.text(0, 30, 'XP: ' + this.currentXP + '/' + this.xpReq, {
            font: '24px Courier New',
            fill: '#fff',
            strokeThickness: 4
        }));
    },
    onDPS: function() {
        if (this.player.dps > 0) {
            if (this.currentMonster && this.currentMonster.alive) {
                let dmg = this.player.dps / 10;
                this.currentMonster.damage(dmg);
                // update the health text
                this.monsterHealthText.text = this.currentMonster.alive ? Math.round(this.currentMonster.health) + '/' + this.currentMonster.maxHealth : 'DEAD';
            }
        }
    },
    onUpgradeButtonClick: function(button, pointer) {
        // make this a function so that it updates after we buy
        function getAdjustedCost() {
            return Math.ceil(button.details.cost + (button.details.level * 1.46));
        }

        if (this.player.gold - getAdjustedCost() >= 0) {
            this.player.gold -= getAdjustedCost();
            this.playerGoldText.text = 'Gold: ' + this.player.gold;
            button.details.level++;
            button.text.text = button.details.name + ': ' + button.details.level;
            button.costText.text = 'Cost: ' + getAdjustedCost();
            button.details.purchaseHandler.call(this, button, this.player);
        }
    },
    onClickCoin: function(coin) {
        if (!coin.alive) {
            return;
        }
        // give the player gold
        this.player.gold += coin.goldValue;
        // update UI
        this.playerGoldText.text = 'Gold: ' + this.player.gold;
        // remove the coin
        coin.kill();
    },
    onKilledMonster: function(monster) {
        // move the monster off screen again
        monster.position.set(1000, this.game.world.centerY);

        let coin;
        // spawn a coin on the ground
        coin = this.coins.getFirstExists(false);
        coin.reset(this.game.world.centerX + this.game.rnd.integerInRange(-100, 100), this.game.world.centerY);
        coin.goldValue = Math.round(this.level * 1.33);
        this.game.time.events.add(Phaser.Timer.SECOND * 3, this.onClickCoin, this, coin);

        this.currentXP += (10 * this.level);
        this.killCount ++;
        console.log(this.killCount);

        if (this.currentXP >= this.xpReq) {
            this.level++;
            this.currentXP = 0;
            this.xpReq = this.xpReq * 2;
        }

        this.levelText.text = 'Level: ' + this.level;
        this.levelKillsText.text = 'XP: ' + this.currentXP + '/' + this.xpReq;

        // pick a new monster
        this.currentMonster = this.monsters.getRandom();
        // upgrade the monster based on level
        this.currentMonster.maxHealth = Math.ceil(this.currentMonster.details.maxHealth + ((this.level - 1) * 10.6));
        // make sure they are fully healed
        this.currentMonster.revive(this.currentMonster.maxHealth);
    },
    onRevivedMonster: function(monster) {
        monster.position.set(this.game.world.centerX - 200, this.game.world.centerY + 100);
        // update the text display
        this.monsterNameText.text = monster.details.name;
        this.monsterHealthText.text = monster.health + "/" + monster.health;
    },
    onClickMonster: function(monster, pointer) {
        // uses random number to create 30% critical hit chance
        let critNum = Math.floor(Math.random() * 10);
        let critHit = false;
        if(critNum === 5 || critNum === 8 || critNum === 3){
        this.currentMonster.damage(this.player.strength * 2)
        critHit = true;
        } else {
            this.currentMonster.damage(this.player.strength)
            critHit = false;
        }

        // grab a damage text from the pool to display what happened
        var dmgText = this.dmgTextPool.getFirstExists(false);
        if (dmgText) {
            // Displays different output depending on type of hit
            if(critHit){
                dmgText.text = "CRITICAL HIT"
            } else {
            dmgText.text = this.player.strength;
            }
            dmgText.reset(pointer.positionDown.x, pointer.positionDown.y);
            dmgText.alpha = 1;
            dmgText.tween.start();
        }

        // update the health text
        this.monsterHealthText.text = this.currentMonster.alive ? 'HP:' + this.currentMonster.health + '/' + this.currentMonster.maxHealth : 'DEAD';
    }
});

game.state.start('play');
