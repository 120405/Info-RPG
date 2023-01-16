package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class TitleScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final Texture img;
    private final Stage stage;
    private final String name;
    private final BitmapFont font;

public TitleScreen(SpriteBatch batch, String name) {
    this.name = name;
    stage = new Stage();
    font = new BitmapFont();
    font.setColor(Color.OLIVE);
    font.getData().setScale(20f);
    stage.clear();
    this.batch = batch;
    show();
    img = new Texture("Background.png");
}
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, name, (int)(Gdx.graphics.getWidth()/2)-font.getData().padRight*15, (int)(Gdx.graphics.getHeight()/2)+250);
        batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Spiel.INSTANCE.gameScreen();
        }
        stage.act(delta);
        stage.draw();
    }
    public void dispose() {
    if(batch != null) {batch.dispose();}
    img.dispose();
    stage.dispose();

    }
    public void hide() {
    stage.clear();
    }
    public void show() {
        Buttons start = new Buttons("Start", stage, "game", 2.21, 2.5, Color.OLIVE);
        Buttons options = new Buttons("Options", stage, "options", 2.21, 3, Color.OLIVE);
        Buttons quit = new Buttons("Quit", stage, "quit", 2.21, 3.7, Color.OLIVE);
    }
}