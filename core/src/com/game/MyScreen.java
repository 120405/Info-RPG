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
    Texture imgHero;
    Texture imgMonster;
    Monster monster;
    Sprite HeroSprite;
    Sprite MonsterSprite;
    Hero hero;
    Fight fight;
    // stuff aus Spiel per instance holen
    public MyScreen() {
        create();
    }
    public void create() {

        monster = new Monster();
        hero = new Hero();
        monster.setATK(20);
        monster.setFullLP(60);
        monster.setLP(60);
        hero.setATK(20);
        hero.setFullLP(100);
        hero.setLP(100);
        fight = new Fight();
        batch = new SpriteBatch();
        img = new Texture("Background.png");
        imgHero = new Texture("Hero.png");
        imgMonster = new Texture("Monster.png");
        HeroSprite = new Sprite(imgHero);
        MonsterSprite = new Sprite(imgMonster);
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


    }
    public void dispose () {
        batch.dispose();
        if(img != null) {
            img.dispose();
        }
        if(imgHero != null) {
            imgHero.dispose();
        }
        if(imgMonster != null) {
            imgMonster.dispose();
        }
    }
}