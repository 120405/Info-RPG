package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shop extends ScreenAdapter {

    SpriteBatch batch;
    Texture img;
    Inventory inventory;
    private Stage stage;

    public Shop(SpriteBatch batch) {
        this.batch = batch;
        img = new Texture(Gdx.files.internal("Albedo.png"));
        inventory = Spiel.INSTANCE.getInventory();
        stage = new Stage();
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

    public void hide() {
        stage.clear();
    }
    public void show() {
        Buttons sword = new Buttons("Sword", stage, "buySword", 1.5, 1.5, Color.WHITE);
        Buttons shield = new Buttons("Shield", stage, "buyShield", 3.5, 1.5, Color.WHITE);
    }

}
