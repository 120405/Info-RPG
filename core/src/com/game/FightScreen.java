package com.game;

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

import java.util.Random;

public class FightScreen extends ScreenAdapter {

    private final SpriteBatch batch;
    private Random r;
    private Sprite HeroSprite;
    private Sprite MonsterSprite;
    private Hero hero;
    private Monster monster;
    private int healthMonster, healthHero;
    private Texture img;
    private Stage stage;
    private int atkH,atkM = 0;

    public FightScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    public void create() {
        stage = new Stage();
        r = new Random();
        img = new Texture("Background.png");
        hero = Spiel.INSTANCE.fight.getHero();
        monster = Spiel.INSTANCE.fight.getMonster();
        healthHero = hero.getFullLP();
        healthMonster = monster.getFullLP();
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
        animate();
        HeroSprite.draw(batch);
        MonsterSprite.draw(batch);
        Spiel.INSTANCE.getFont().draw(batch, hero.getName(), (int) (hero.getFullLP() / 2), 61);
        Spiel.INSTANCE.getFont().draw(batch, monster.getName(), Gdx.graphics.getWidth() - (45 + (int) (monster.getFullLP() / 2)), 61);
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
            atkH = hero.attack();
            atkM = monster.attack();
            String winner = Spiel.INSTANCE.fight.fight();
            healthMonster = monster.getLP();
            healthHero = hero.getLP();
            if (winner.equals("Hero")) {
                MonsterSprite.setAlpha(0);
                //death animation
                Spiel.INSTANCE.gameScreen();
            } else if (winner.equals("Monster")) {
                HeroSprite.setAlpha(0);
                //death animation
                Spiel.INSTANCE.gameScreen();
            }
            hero.setRandom(0);
        }
    }

    public void animate() {
        //TODO wait:seconds & animation
        Spiel.INSTANCE.getFont().draw(batch, "-" + atkM, 200, 500);
        Spiel.INSTANCE.getFont().draw(batch, "-" + atkH, 500, 500);
    }

    public void show() {

        Buttons inv = new Buttons("Inventory", stage, "showInv", 16, 3, Color.OLIVE);
        stage.addActor(Spiel.INSTANCE.getInventory().getInventory());

    }

    public void hide() {
        stage.clear();
    }

}
