package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TitleScreen extends ScreenAdapter{
    SpriteBatch batch;
    Texture img;

    public TitleScreen() {
        batch = new SpriteBatch();
        img = new Texture("e.png");

    }
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Spiel.INSTANCE.setScreen(new MyScreen());


        }


    }
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
    public void hide() {
        this.dispose();
    }
}
