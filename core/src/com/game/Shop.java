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
import com.game.items.Inventory;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shop extends ScreenAdapter {

    private SpriteBatch batch;
    private Texture img;
    private Inventory inventory;
    private Stage stage;
    private BitmapFont fontSw;
    private BitmapFont fontSh;
    private BitmapFont fontDa;
    private BitmapFont fontMo;
    private Buttons sword;
    private Buttons shield;
    private Buttons dagger;
    private double xShield;
    private double xSword;
    private double xDagger;


    public Shop(SpriteBatch batch) {
        xShield = 1.5;
        xSword = 3;
        xDagger = 2;
        this.batch = batch;
        img = new Texture(Gdx.files.internal("Albedo.png"));
        inventory = Spiel.INSTANCE.getInventory();
        fontSw = new BitmapFont();
        fontSw.setColor(Color.WHITE);
        fontSw.getData().setScale(3f);
        fontSh = new BitmapFont();
        fontSh.setColor(Color.WHITE);
        fontSh.getData().setScale(3f);
        fontDa = new BitmapFont();
        fontDa.setColor(Color.WHITE);
        fontDa.getData().setScale(3f);
        fontMo = new BitmapFont();
        fontMo.setColor(Color.WHITE);
        fontMo.getData().setScale(3f);
        stage = new Stage();
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0);

        fontSw.draw(batch, "Worth: " + Spiel.INSTANCE.getInventory().getSword().getWorth(), (int)(Gdx.graphics.getWidth()/xSword - 30)-fontSw.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.7));
        fontSw.draw(batch, "ATK: " + Spiel.INSTANCE.getInventory().getSword().getAtk(), (int)(Gdx.graphics.getWidth()/xSword - 30)-fontSw.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.8));
        fontSw.draw(batch, "Dur: " + Spiel.INSTANCE.getInventory().getSword().getDur(), (int)(Gdx.graphics.getWidth()/xSword - 30)-fontSw.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.9));

        fontSh.draw(batch, "Worth: " + Spiel.INSTANCE.getInventory().getShield().getWorth(), (int)(Gdx.graphics.getWidth()/xShield-30)-fontSh.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.7));
        fontSh.draw(batch, "DEF: " + Spiel.INSTANCE.getInventory().getShield().getDef(), (int)(Gdx.graphics.getWidth()/xShield-30)-fontSh.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.8));
        fontSh.draw(batch, "Dur: " + Spiel.INSTANCE.getInventory().getShield().getDur(), (int)(Gdx.graphics.getWidth()/xShield-30)-fontSh.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.9));

        fontDa.draw(batch, "Worth: " + Spiel.INSTANCE.getInventory().getDagger().getWorth(), (int)(Gdx.graphics.getWidth()/xDagger-30)-fontSh.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.7));
        fontDa.draw(batch, "ATK: " + Spiel.INSTANCE.getInventory().getDagger().getAtk(), (int)(Gdx.graphics.getWidth()/xDagger-30)-fontSh.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.8));
        fontDa.draw(batch, "Dur: " + Spiel.INSTANCE.getInventory().getDagger().getDur(), (int)(Gdx.graphics.getWidth()/xDagger-30)-fontSh.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.9));

        fontMo.draw(batch, "Money: " + Spiel.INSTANCE.getMoney(), (int)(Gdx.graphics.getWidth()/2)-fontSh.getData().padRight*15, (int)(Gdx.graphics.getHeight()/1.3));

        batch.end();
        green();


        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Spiel.INSTANCE.gameScreen();
        }

        stage.act(delta);
        stage.draw();
    }

    public void green(){
        if(Spiel.INSTANCE.fight.getHero().getWeapon() == Spiel.INSTANCE.getInventory().getSword()){
            fontSw.setColor(Color.GREEN);
            fontDa.setColor(Color.WHITE);
        }
        if(Spiel.INSTANCE.fight.getHero().getWeapon() == Spiel.INSTANCE.getInventory().getDagger()){
            fontDa.setColor(Color.GREEN);
            fontSw.setColor(Color.WHITE);
        }
        if(Spiel.INSTANCE.fight.getHero().getShield() == Spiel.INSTANCE.getInventory().getShield()){
            fontSh.setColor(Color.GREEN);
        }
    }

    public void dispose() {
        if(batch != null) {batch.dispose();}
        if(img != null) {img.dispose();}
    }

    public void hide() {
        stage.clear();
    }
    public void show() {
         sword = new Buttons("Sword", stage, "buySword", xSword, 1.5, Color.WHITE);
         shield = new Buttons("Shield", stage, "buyShield", xShield, 1.5, Color.WHITE);
        dagger = new Buttons("Dagger", stage, "buyDagger", xDagger, 1.5, Color.WHITE);
    }
}
