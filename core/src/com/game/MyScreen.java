package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyScreen extends ScreenAdapter  {
    SpriteBatch batch;
    Texture img;
    Texture imgHero;
    Texture imgMonster;
    Monster monster;
    Hero hero;
    Fight fight;
    // stuff aus Spiel per instance holen
    public MyScreen() {

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
    }
    public void render (float delta) {
      ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.draw(imgHero, 100, 50);
        batch.draw(imgMonster, 0, -100);
        batch.end();
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if(fight.fight(monster, hero).equals("Hero")) {
                imgMonster.dispose();
            } else if(fight.fight(monster, hero).equals("Monster")){
                imgHero.dispose();

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
