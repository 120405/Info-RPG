package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;


public class FightScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private Sprite HeroSprite;
    private Sprite MonsterSprite;
    private int healthMonster, healthHero;
   ShapeRenderer shapeRenderer;
        public FightScreen(SpriteBatch batch) {
            this.batch = batch;
           create();
        }
        public void create() {
            healthHero = Spiel.INSTANCE.fight.getHero().getFullLP();
            healthMonster = Spiel.INSTANCE.fight.getMonster().getFullLP();
          shapeRenderer = new ShapeRenderer();
            HeroSprite = new Sprite(new Texture("Hero.png"));
            MonsterSprite = new Sprite(new Texture("Monster.png"));
            HeroSprite.setPosition(100, 100);
            MonsterSprite.setPosition(130, -40);
            MonsterSprite.flip(true, false);
        }
  public void render(float delta) {
            ScreenUtils.clear(0, 0, 0, 1);
            Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
            createHealthBars();
      if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
         String winner = Spiel.INSTANCE.fight.fight();
          healthMonster = Spiel.INSTANCE.fight.getMonster().LP;
          healthHero = Spiel.INSTANCE.fight.getHero().LP;
          if(winner.equals("Hero")) {
              MonsterSprite.setAlpha(0);
          } else if(winner.equals("Monster")){
              HeroSprite.setAlpha(0);
          }
      }
            batch.begin();
            HeroSprite.draw(batch);
            MonsterSprite.draw(batch);
            batch.end();
        }
        public void dispose() {
            batch.dispose();
        }

    public void createHealthBars() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(20, 20, healthHero, 20);
        shapeRenderer.rect(500, 20, healthMonster, 20);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(19, 19, Spiel.INSTANCE.fight.getHero().getFullLP()+1, 21);
        shapeRenderer.rect(499, 19, Spiel.INSTANCE.fight.getMonster().getFullLP()+1, 21);
        shapeRenderer.end();
    }
}
