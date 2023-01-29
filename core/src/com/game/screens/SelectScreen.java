package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.Buttons;

public class SelectScreen extends ScreenAdapter {
	private final SpriteBatch batch;
	private Texture img;
	private Stage stage;
	
	public SelectScreen(SpriteBatch batch) {
			this.batch = batch;
			stage = new Stage();
		    stage.clear();
		    img = new Texture("Background.png");
		    show();
	}
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act(delta);
        stage.draw();
	}
	public void dispose() {
		 batch.dispose();
		 stage.dispose();
	}
	public void hide() {
		stage.clear();
	}
	public void show() {
		new Buttons("Back", stage, "title", 2, 2.5, Color.BLACK);
		 new Buttons("Start Game1", stage, "loadGame", 2.21, 3, Color.OLIVE);
		 new Buttons("New Game", stage, "game", 2.21, 3.5, Color.OLIVE); // soll ganz neues spiel laden
	}
}
