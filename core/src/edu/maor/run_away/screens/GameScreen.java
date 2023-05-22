package edu.maor.run_away.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

import edu.maor.run_away.RunAway;
import edu.maor.run_away.entities.Enemy;
import edu.maor.run_away.entities.Player;

public class GameScreen implements Screen, InputProcessor {

    Player player;
    List<Enemy> enemies;
    private final RunAway game;
    private final Sprite background;
    OrthographicCamera camera;

    private int touchX, touchY, gi;

    public GameScreen(RunAway game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);

        background = new Sprite( new Texture("background.png"));
        startEntities();
        Gdx.input.setInputProcessor(this);
    }

    private void startEntities() {
        TextureRegion[][] ghostRegion = TextureRegion.split(new Texture("ghost.png"), 114, 110);
        TextureRegion[] red = {ghostRegion[0][0], ghostRegion[0][1], ghostRegion[1][0], ghostRegion[1][1]} ;
        TextureRegion[] blue = {ghostRegion[0][2], ghostRegion[0][3], ghostRegion[1][2], ghostRegion[1][3]};
        TextureRegion[] pink = {ghostRegion[2][0], ghostRegion[2][1], ghostRegion[3][0], ghostRegion[3][1]};
        TextureRegion[] orange = {ghostRegion[2][2], ghostRegion[2][3], ghostRegion[3][2], ghostRegion[3][3]};
        player = new Player(game, TextureRegion.split(new Texture("runner.png"),108, 140));
        enemies = new ArrayList<>();
        enemies.add(new Enemy(game, blue));
        enemies.add(new Enemy(game, pink));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Setup methods
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        for (Enemy enemy : enemies) enemy.move();
        //Draw methods
        game.batch.draw(background, 0, 0, game.screenWidth, game.screenHeight);
        player.sprite.draw(game.batch);
        for (Enemy enemy : enemies) {
            enemy.sprite.draw(game.batch);
            //Check colisions
            if (player.sprite.getBoundingRectangle().overlaps(enemy.sprite.getBoundingRectangle())) {
                game.setScreen(new GameOverScreen(game));
            }
        }

        game.batch.end();

        // game.setScreen(new GameOverScreen(game))

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchX = screenX;
        touchY = screenY;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (gi >= 32) {
            gi = 0;
        }
        float diffX = screenX - touchX;
        float diffY = screenY - touchY;

        player.move( diffX/64, diffY/64, gi/8);
        gi++;

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
