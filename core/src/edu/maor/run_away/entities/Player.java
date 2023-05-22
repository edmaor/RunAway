package edu.maor.run_away.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.maor.run_away.RunAway;

public class Player {
    RunAway game;
    private TextureRegion[][] animations;
    private Sprite[][] movements;
    public Sprite sprite;

    public float x, y;

    public Player (RunAway game, TextureRegion[][] animations) {
        this.game = game;
        movements = new Sprite[animations.length][animations[0].length];
        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[0].length; j++) {
                movements[i][j] = new Sprite(animations[i][j]);
            }
        }
        sprite = movements[0][0];
        this.x = this.game.screenWidth/2;
        this.y = this.game.screenHeight/2;
        this.move(0, 0, 0);
    }

    public void move(float x , float y, int gi) {
        this.x += x;
        this.y -= y;
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
        if (x > 0) sprite = movements[0][gi];
        else sprite = movements[1][gi];
        sprite.setPosition(this.x, this.y);
    }
}
