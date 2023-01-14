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
    SpriteBatch batch;
    Texture img;
    Monster monster;
    Sprite HeroSprite;
    Sprite MonsterSprite;
    Hero hero;
    Fight fight;
    //Animator animator;
    // stuff aus Spiel per instance holen
    public MyScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }
    public void create() {
        monster = new Monster(60, 60, 20, "");
        hero = new Hero(100, 100, 20, "");
        animator = new Animator();
        fight = new Fight();
        img = new Texture("Background.png");
        HeroSprite = new Sprite(new Texture("Hero.png"));
        MonsterSprite = new Sprite(new Texture("Monster.png"));
        HeroSprite.setPosition(300, 300);
        MonsterSprite.setPosition(500, 300);
    }
    public void render (float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        batch.begin();
        batch.draw(img, 0, 0);

        if(!Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            HeroSprite.draw(batch);
        }
        MonsterSprite.draw(batch);
        batch.end();
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if(fight.fight(monster, hero).equals("Hero")) {
                HeroSprite.setAlpha(0);
            } else if(fight.fight(monster, hero).equals("Monster")){
                HeroSprite.setAlpha(0);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
             Spiel.INSTANCE.titleScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            Spiel.INSTANCE.shopScreen();
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