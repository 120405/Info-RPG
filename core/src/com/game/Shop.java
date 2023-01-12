package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Shop extends ScreenAdapter {

    SpriteBatch batch;
    Texture img;

    public Shop() {
        batch = new SpriteBatch();
        img = new Texture("Albedo.png");
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            Spiel.INSTANCE.setScreen(new MyScreen());
        }
    }

}
