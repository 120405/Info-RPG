package com.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;


public class FightAnimator implements ApplicationListener {
    private SpriteBatch batch;
    private HashMap<String, Animation<TextureRegion>> heroAnim;
    private HashMap<String, Animation<TextureRegion>> monsterAnim;
    private float stateTime;
    private Sprite heroSprite,monsterSprite;
    private String currentHeroAnim, currentMonsterAnim;
    public FightAnimator() {
        heroAnim = new HashMap<String, Animation<TextureRegion>>();
        monsterAnim = new HashMap<String, Animation<TextureRegion>>();
        create();
    }
    @Override
    public void create() {
    batch = new SpriteBatch();
    stateTime = 0f;
    createAnimationMaps();
    }

    @Override
    public void resize(int width, int height) {

    }
    public void createAnimationMaps() {
        heroAnim.put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Attack1.png")), 1, 5, 0.2f, 0));
        heroAnim.put("Idle", createAnim(new Texture(Gdx.files.internal("FightAnimations/Hero/Idle.png")), 1, 4, 0.2f));
        heroAnim.put("Dead",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Dead.png")), 1, 6, 0.2f, 0));

        monsterAnim.put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster/Attack.png")), 4, 4, 0.2f, 0));
        monsterAnim.put("Idle",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster/Idle.png")), 5, 4, 0.2f, 0));
        currentHeroAnim = "Idle";
        currentMonsterAnim = "Idle";
    }
    public Animation<TextureRegion> createAnimRow(Texture t, int row, int col, float frameTime, int col1) {
        Animation<TextureRegion> Anim = null;
        TextureRegion[][] tmp = TextureRegion.split(t, t.getWidth() / col, t.getHeight() / row);
        TextureRegion[] TR = new TextureRegion[col * row];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                TR[index++] = tmp[col1][j];
            }
            Anim = new Animation<>(frameTime, TR);
        }
        return Anim;
    }
    public Animation<TextureRegion> createAnim(Texture t, int row, int col, float frameTime) {
        Animation<TextureRegion> Anim = null;
        TextureRegion[][] tmp = TextureRegion.split(t, t.getWidth() / col, t.getHeight() / row);
        TextureRegion[] TR = new TextureRegion[col * row];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                TR[index++] = tmp[i][j];
            }
            Anim = new Animation<>(frameTime, TR);
        }
        return Anim;
    }
    @Override
    public void render() {

        stateTime += Gdx.graphics.getDeltaTime();
        if(currentHeroAnim != "Dead") {
            if (heroAnim.get(currentHeroAnim).isAnimationFinished(stateTime)) {
                stateTime = 0f;
                if (currentHeroAnim == "Attack") {
                    currentHeroAnim = "Idle";
                }
            }
        }
        heroSprite = new Sprite(heroAnim.get(currentHeroAnim).getKeyFrame(stateTime, false));
        heroSprite.setPosition(150,250);
        heroSprite.setScale(4f);
        monsterSprite = new Sprite(monsterAnim.get(currentMonsterAnim).getKeyFrame(stateTime, true));
        monsterSprite.setScale(2f);
        monsterSprite.setPosition(450,50);
        monsterSprite.flip(true, false);
        batch.begin();
        heroSprite.draw(batch);
        monsterSprite.draw(batch);
        batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    batch.dispose();
    }
    public void setCurrentHeroAnim(String anim) {
        currentHeroAnim = anim;
    }
    public void setCurrentMonsterAnim(String anim) {
        currentMonsterAnim = anim;
    }
}
