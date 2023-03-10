package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class FightScreen extends ScreenAdapter {

    private final SpriteBatch batch;
    private Random r;
    private Hero hero;
    private Monster monster;
    private int healthMonster, healthHero;
    private Texture img;
    private Stage stage;
    private int atkH,atkM = 0;
   private FightAnimator fa;
   private Spiel game;

    public FightScreen(SpriteBatch batch) {
        this.batch = batch;
        game = Spiel.INSTANCE;
        create();
    }

    public void create() {
        fa = new FightAnimator();
        stage = new Stage();
        r = new Random();
        img = new Texture("Background.png");
        hero = game.fight.getHero();
        monster = game.fight.getMonster();
        healthHero = hero.getFullLP();
        healthMonster = monster.getFullLP();
        show();
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        game.createHealthBars(true);
        handleInput();
        batch.begin();
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
            game.gameScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && hero.getStatus() && monster.getStatus()) {
            fa.setCurrentHeroAnim("Attack");
            atkH = hero.attack();
            atkM = monster.attack();
            String winner = game.fight.fight();
            healthMonster = monster.getLP();
            healthHero = hero.getLP();
            if (winner.equals("Hero")) {
                fa.setCurrentHeroAnim("Dead");

            } else if (winner.equals("Monster")) {
                //death animation

            }
            hero.setRandom(0);
        }
    }

    public void animate() {
        //TODO wait:seconds & animation
        game.getFont().draw(batch, "-" + atkM, 200, 500);
        game.getFont().draw(batch, "-" + atkH, 500, 500);
        fa.render();
        game.getFont().draw(batch, hero.getName(), (int) (hero.getFullLP() / 2), 61);
        game.getFont().draw(batch, monster.getName(), Gdx.graphics.getWidth() - (45 + (int) (monster.getFullLP() / 2)), 61);
    }

    public void show() {

        Buttons inv = new Buttons("Inventory", stage, "showInv", 16, 3, Color.OLIVE);
        stage.addActor(game.getInventory().getInventory());

    }

    public void hide() {
        stage.clear();
    }

}
