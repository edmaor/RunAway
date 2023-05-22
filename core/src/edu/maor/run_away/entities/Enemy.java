package edu.maor.run_away.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

import edu.maor.run_away.RunAway;

public class Enemy {
    RunAway game;
    private TextureRegion[] animations;
    private Sprite[] movements;
    public Sprite sprite;

    public float x, y;

    Random rnd;

    public Enemy(RunAway game, TextureRegion[] animations) {
        this.game = game;
        this.animations = animations;

        rnd = new Random();

        movements = new Sprite[animations.length];
        for (int i = 0; i < movements.length; i++) {
            movements[i] = new Sprite(animations[i]);
        }

        this.sprite = movements[0];
        this.x = rnd.nextFloat();
        this.y = rnd.nextFloat();
        this.sprite.setPosition(this.x, this.y);
    }

    public void move() {
        int xmov = rnd.nextInt(24) -12;
        int ymov = rnd.nextInt(24) - 12;
        this.x += xmov;
        this.y -= ymov;
        if (this.x > game.screenWidth - sprite.getWidth()) {
            this.x = (int) (game.screenWidth - sprite.getWidth());
        } else if (this.x < 0) {
            this.x = 0;
        }
        if (this.y > game.screenHeight - sprite.getHeight()) {
            this.y = (int) (game.screenHeight - sprite.getHeight());
        } else if (this.y < 0) {
            this.y = 0;
        }
        if (Math.abs(xmov) > Math.abs(ymov)) {
            if (xmov > 0) sprite = movements[1];
            else sprite = movements[3];
        } else {
            if (ymov > 0) sprite = movements[2];
            else sprite = movements[0];
        }
        sprite.setPosition(this.x, this.y);
    }
}
