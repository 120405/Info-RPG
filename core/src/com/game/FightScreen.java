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
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Texture img;

        public FightScreen(SpriteBatch batch) {
            this.batch = batch;
            create();
        }
        public void create() {
            img = new Texture("Background.png");
            font = new BitmapFont();
            font.setColor(Color.WHITE);
            r = new Random();
            hero =  Spiel.INSTANCE.fight.getHero();
            monster = Spiel.INSTANCE.fight.getMonster();
            healthHero = hero.getFullLP();
            healthMonster = monster.getFullLP();
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
            batch.begin();
            batch.draw(img, 0, 0);
            batch.end();
            if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                hero.setRandom(r.nextInt(21));
            }
            createHealthBars();
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                String winner = Spiel.INSTANCE.fight.fight();
                healthMonster = monster.getLP();
                healthHero = hero.getLP();
                if(winner.equals("Hero")) {
                     MonsterSprite.setAlpha(0);
                } else if(winner.equals("Monster")){
                     HeroSprite.setAlpha(0);
                }
            }
            batch.begin();
            HeroSprite.draw(batch);
            MonsterSprite.draw(batch);
            font.draw(batch, hero.getName(), (int)(hero.getFullLP()/2), 61);
            font.draw(batch, monster.getName(), 480+((int)(monster.getFullLP()/2)), 61);
            batch.end();
        }
        public void dispose() {
            batch.dispose();
            img.dispose();
            font.dispose();
            shapeRenderer.dispose();
         }

        public void createHealthBars() {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(20, 20, healthHero, 20);
            shapeRenderer.rect(500, 20, healthMonster, 20);
            shapeRenderer.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(19, 19, hero.getFullLP()+1, 21);
            shapeRenderer.rect(499, 19, monster.getFullLP()+1, 21);
            shapeRenderer.end();
    }
}
