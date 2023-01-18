package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;

public class Options extends ScreenAdapter {
    private final SpriteBatch batch;
    private Texture img;
    private Stage stage;
    private Window window;

    public Options(SpriteBatch batch) {
        this.batch = batch;
        create();
    }
    public void create() {
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
    }
    public void hide() {
        stage.clear();
    }
    public void show() {
        Buttons options = new Buttons("Back", stage, "title", 2, 2.5, Color.BLACK);
    }
}
