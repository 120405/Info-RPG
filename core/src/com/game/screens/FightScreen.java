package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.Buttons;
import com.game.Spiel;
import com.game.fight.Hero;
import com.game.fight.Monster;
import com.game.items.GUI;

import java.util.Random;

public class FightScreen extends ScreenAdapter {

    private final SpriteBatch batch;
    private Random r;
    private Sprite HeroSprite;
    private Sprite MonsterSprite;
    private Hero hero;
    private Monster monster;
    private Texture img;
    private Stage stage;
    public GUI inventory;

    public FightScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    public void create() {
        stage = new Stage();
        r = new Random();
        inventory = Spiel.INSTANCE.getInventoryGUI();
        img = new Texture("Background.png"); 
        hero = Spiel.INSTANCE.fight.getHero();
        monster = Spiel.INSTANCE.fight.getMonster();
        HeroSprite = new Sprite(new Texture("Hero.png"));
        HeroSprite.setScale(2f);
        HeroSprite.setPosition(100, 90);
        MonsterSprite = new Sprite(new Texture("Monster.png"));
        MonsterSprite.setScale(0.5f);
        MonsterSprite.setPosition(100, -140);
        MonsterSprite.flip(true, false);
        show();
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); 
        batch.end();
        Spiel.INSTANCE.createHealthBars(true);
        handleInput();
        batch.begin();
        HeroSprite.draw(batch);
        MonsterSprite.draw(batch);
        animate();
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }


    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R) && hero.getRandom() == 0) {
            hero.setRandom(r.nextInt(21));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Spiel.INSTANCE.gameScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && hero.getStatus() && monster.getStatus()) {
            String winner = Spiel.INSTANCE.fight.fight();
            if (winner.equals("Hero")) {
                MonsterSprite.setAlpha(0);
            } else if (winner.equals("Monster")) {
                Spiel.INSTANCE.titleScreen();
            }
            hero.setRandom(0);
        }
    }

    public void animate() {
        //TODO wait:seconds & animation
    	Spiel.INSTANCE.getFont().draw(batch, "-" + monster.attack(), 200, 500);
        Spiel.INSTANCE.getFont().draw(batch, "-" + hero.attack(), 500, 500);
    }

    public void show() {

        new Buttons("Inventory", stage, "showInv", 16, 3, Color.OLIVE);
        new Buttons("Back", stage, "toGame", 2, 3, Color.OLIVE);
        stage.addActor(inventory.getInventory());

    }

    public void hide() {
        stage.clear();
        inventory.hide();
    }

}
