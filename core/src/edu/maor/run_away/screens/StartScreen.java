package edu.maor.run_away.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import edu.maor.run_away.RunAway;

public class StartScreen implements Screen {
    private final RunAway game;
    private final Sprite background;
    OrthographicCamera camera;

    public StartScreen(RunAway game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);

        background = new Sprite( new Texture("background.png"));
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0, game.screenWidth, game.screenHeight);
        game.font.draw(game.batch, "Tap anywhere to begin!", (game.screenWidth/2-220), game.screenHeight/2);
        game.font.draw(game.batch, "Avoid the ghosts!!!", (game.screenWidth/2-200), 200);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

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
}
