package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyScreen extends ScreenAdapter  {
    private final SpriteBatch batch;
    Animator animator;
    Texture img;

    public MyScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }
    public void create() {
        animator = new Animator();
        img = new Texture("Background.png");

    }
    public void render (float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
             Spiel.INSTANCE.titleScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            Spiel.INSTANCE.shopScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            Spiel.INSTANCE.fightScreen();
        }
        animator.render();
    }
    public void dispose () {
        batch.dispose();
        if(img != null) {
            img.dispose();

        }
    }
}