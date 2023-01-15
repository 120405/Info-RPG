package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Options extends ScreenAdapter {
    private SpriteBatch batch;
    private Texture img;

    public Options(SpriteBatch batch) {
        this.batch = batch;
        create();
    }
    public void create() {
        img = new Texture("Background.png");
    }
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0, 1920, 1080);
        batch.end();
    }
    public void dispose() {
        batch.dispose();
    }
}
