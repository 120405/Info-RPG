package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TitleScreen extends ScreenAdapter {
    SpriteBatch batch;
    Texture img;
    BitmapFont font;
public TitleScreen(SpriteBatch batch) {
    this.batch = batch;
    img = new Texture("e.png");
    font = new BitmapFont();
    font.setColor(Color.RED);

}

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0);
        font.draw(batch, "Press Space to start Game!", 200, 200);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Spiel.INSTANCE.gameScreen();
        }
    }

    public void dispose() {
    if(batch != null) {batch.dispose();}
    if(img != null) {img.dispose();}
    }
}