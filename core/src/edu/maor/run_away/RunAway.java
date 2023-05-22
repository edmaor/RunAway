package edu.maor.run_away;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.maor.run_away.screens.StartScreen;

public class RunAway extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	Music music;
	public float screenWidth, screenHeight;

	public void loadAssets() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.getData().setScale(3);
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/cawait.mp3"));
		music.setLooping(true);
		music.play();
//		music.stop();
	}
	
	@Override
	public void create () {
		loadAssets();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		this.setScreen(new StartScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
