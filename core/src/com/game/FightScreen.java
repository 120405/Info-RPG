package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class FightScreen extends ScreenAdapter {

    private final SpriteBatch batch;
    private Random r;
    private Hero hero;
    private Monster monster;
    private Texture img;
    private Stage stage;
   private FightAnimator fightAnimator;
   private Spiel game;
   private float sec = 0;
   private boolean fight = false;
   private boolean heroDMG,monsterDMG = false;

    public FightScreen(SpriteBatch batch) {
        this.batch = batch;
        game = Spiel.INSTANCE;
        create();
    }

    public void create() {
        fightAnimator = new FightAnimator(game.fight.getMonster().getType());
        stage = new Stage();
        r = new Random();
        img = new Texture("Background.png");
        hero = game.getHero();
        monster = game.getFight().getMonster();
        show();
    }
    public void render(float delta) {
        sec += delta;
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        handleInput();
        batch.begin();
        animate();
        batch.end();
        stage.act(delta);
        stage.draw();
        if(sec > 5f && fight) {
            if(game.fight.getWinner().equals("Monster")) {
                game.gameScreen();
                fightAnimator.setCurrentHeroAnim("Idle");
                fightAnimator.setCurrentMonsterAnim("Idle");
                game.getFight().reset();
                sec = 0f;
                fight = false;
            }
            game.gameScreen();
            sec = 0f;
            fight = false;
        }

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && hero.getStatus() && monster.getStatus() && fightAnimator.fightAnimFinished()) {
            int healthHero = hero.getLP();
            int healthMonster = monster.getLP();
            fightAnimator.setStateTimeHero(0f);
            fightAnimator.setCurrentHeroAnim("Attack" + MathUtils.random(1,3));
            String winner = game.getFight().fight();
            heroDMG = hero.getLP() < healthHero;
            monsterDMG = monster.getLP() < healthMonster;
            if (winner.equals("Hero")) {
                fightAnimator.setCurrentMonsterAnim("Dead");
                hero.setLP(hero.getLP()+1);
                sec = 0; fight = true;
            } else if (winner.equals("Monster")) {
                sec = 0; fight = true;
            }
            hero.setRandom(0);
        }
    }

    public void animate() {
        fightAnimator.render();
        game.getFont().draw(batch, hero.getName(), (int) (hero.getFullLP() / 2), 61);
        game.getFont().draw(batch, monster.getName(), Gdx.graphics.getWidth() - (45 + (int) (monster.getFullLP() / 2)), 61);
        game.createHealthBars(true);
    }

    public void show() {
        new Buttons("Inventar", stage, "showInv", 16, 3, Color.OLIVE);
        stage.addActor(game.getInventory().getInventory());
        stage.addActor(game.getInventory().getEquipWindow());
        stage.addActor(Spiel.INSTANCE.getInventory().getStatsWindow());
    }

    public void hide() {
        stage.clear();
    }
    public boolean getHeroDMG() {
        return heroDMG;
    }
    public boolean getMonsterDMG() {
        return monsterDMG;
    }
}
