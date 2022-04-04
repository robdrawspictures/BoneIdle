import Phaser from "../lib/phaser.js";
import Enemy from "../game/Enemy.js";

export default class Game extends Phaser.Scene {

    playerLevel = 1
    enemyHP = 100
    playerStrength = 10
    playerXP = 0
    nextLevel = 100
    

    constructor () {
        super('game')
    }

    init() {
        this.playerLevel = 1
        this.enemyHP = 100
        this.playerStrength = 10
        this.playerXP = 0
        this.nextLevel = 100
        this.enemyX = this.scale.width * 0.5
        this.enemyY = (this.scale.height * 0.5) + 70
        this.playerY = 450
        this.bgY = this.scale.height * 0.5
    }

    preload () {
        this.load.image('bg', 'assets/abandonedBlock.png')
        this.load.image('enemy', 'assets/MargeSwing.png')
        this.load.image('player', 'assets/monke.png')
        this.cursors = this.input.keyboard.createCursorKeys();
        this.width = this.displayWidth;
        this.height = this.displayHeight;
    }

    create() {
        this.bg = this.add.image(this.enemyX, this.bgY, 'bg').setScale(0.30)
        this.player = this.physics.add.sprite(200, 450, 'player');
        this.enemy = this.physics.add.sprite(this.enemyX, this.enemyY, 'enemy').setScale(0.5);
        this.drawHUD();
    }

    drawHUD() {
        const hudX = this.width - 200;
        const hudHeight = this.height;
        this.add.rectangle(hudX, 0, 300, 970, 0x6666ff);
    }

}