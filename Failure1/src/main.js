import Phaser from "./lib/phaser.js";
import Game from "./scenes/Game.js";

console.log("Loading...")
console.dir(Phaser)

export default new Phaser.Game({
    type: Phaser.AUTO,
    width: 1024,
    height: 640,
    scene: [Game],
    physics: {
        default: 'arcade',
        arcade: {
            gravity: {
                y: 0
            },
            debug: true
        }
    }
})