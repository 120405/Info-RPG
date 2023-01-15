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
    private SpriteBatch batch;
    private Button quit;
    private Button start;
    private Texture img;
    private Stage stage;

public TitleScreen(SpriteBatch batch) {

    this.batch = batch;
    stage = new Stage();
    quit = new Button("Quit", stage, "quit", 600, 400);
    start = new Button("Start", stage, "game", 300, 400);
    img = new Texture("e.png");
}
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Spiel.INSTANCE.gameScreen();
        }
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
    if(batch != null) {batch.dispose();}
    if(img != null) {img.dispose();}
    }
}