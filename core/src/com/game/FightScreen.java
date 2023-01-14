package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class FightScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    Sprite HeroSprite;
    Sprite MonsterSprite;
        public FightScreen(SpriteBatch batch) {
            this.batch = batch;
            create();
        }
        public void create() {
            HeroSprite = new Sprite(new Texture("Hero.png"));
            MonsterSprite = new Sprite(new Texture("Monster.png"));
            HeroSprite.setPosition(300, 300);
            MonsterSprite.setPosition(500, 300);
        }
        public void render(float delta) {
            ScreenUtils.clear(0, 0, 0, 1);
            Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
            batch.begin();
          //  batch.draw(img, 0, 0);

                HeroSprite.draw(batch);

            MonsterSprite.draw(batch);
            batch.end();
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if(Spiel.INSTANCE.fight.fight().equals("Hero")) {
                    MonsterSprite.setAlpha(0);
                } else if(Spiel.INSTANCE.fight.fight().equals("Monster")){
                    HeroSprite.setAlpha(0);
                }
            }
        }
        public void dispose() {
            batch.dispose();
        }
}
