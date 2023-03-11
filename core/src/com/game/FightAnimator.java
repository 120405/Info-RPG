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
    private HashMap<Integer, HashMap<String, Animation<TextureRegion>>> monsters;
    private float stateTimeHero,stateTimeMonster;
    private Sprite heroSprite,monsterSprite;
    private String currentHeroAnim, currentMonsterAnim;
    private int monsterType = 1;
    public FightAnimator(int monsterType) {
        this.monsterType = monsterType;
        heroAnim = new HashMap<>();
        monsters = new HashMap<>();
        create();

    }
    @Override
    public void create() {
    batch = new SpriteBatch();
    stateTimeHero = 0f;
    stateTimeMonster = 0f;
    monsters.put(1,new HashMap<String, Animation<TextureRegion>>());
    createAnimationMaps();
    }

    @Override
    public void resize(int width, int height) {

    }
    public void createAnimationMaps() {
        heroAnim.put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Attack1.png")), 1, 5, 0.2f, 0));
        heroAnim.put("Idle", createAnim(new Texture(Gdx.files.internal("FightAnimations/Hero/Idle.png")), 1, 4, 0.2f));
        heroAnim.put("Dead",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Dead.png")), 1, 6, 0.2f, 0));

        monsters.get(1).put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster2/Attack1.png")), 1, 5, 0.2f, 0));
        monsters.get(1).put("Idle",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster2/Idle.png")), 1, 4, 0.2f, 0));
        monsters.get(1).put("Dead",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Dead.png")), 1, 6, 0.2f, 0));

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
        stateTimeHero += Gdx.graphics.getDeltaTime();
        stateTimeMonster += Gdx.graphics.getDeltaTime();
        if(!currentHeroAnim.equals("Dead")) {
            if (heroAnim.get(currentHeroAnim).isAnimationFinished(stateTimeHero)) {
                stateTimeHero = 0f;
                if (currentHeroAnim.equals("Attack")) {
                    currentHeroAnim = "Idle";
                    if(!currentMonsterAnim.equals("Dead")) {
                        currentMonsterAnim = "Attack";
                    }


                }
            }
        }

        heroSprite = new Sprite(heroAnim.get(currentHeroAnim).getKeyFrame(stateTimeHero, false));

        if(!currentMonsterAnim.equals("Dead")) {
            if(monsters.get(monsterType).get(currentMonsterAnim).isAnimationFinished(stateTimeMonster)) {
                stateTimeMonster = 0f;
                if(currentMonsterAnim.equals("Attack")) {
                    currentMonsterAnim = "Idle";
                    if(Spiel.INSTANCE.fight.getWinner().equals("Monster")) {
                        currentHeroAnim = "Dead";
                    }

                }
            }
        }

        monsterSprite = new Sprite(monsters.get(monsterType).get(currentMonsterAnim).getKeyFrame(stateTimeMonster, false));
        position();
        batch.begin();
        heroSprite.draw(batch);
        monsterSprite.draw(batch);
        batch.end();
    }
    public void position() {
        heroSprite.setPosition(150,250);
        heroSprite.setScale(4f);
        monsterSprite.setScale(4f);
        monsterSprite.setPosition(450,250);
        monsterSprite.flip(true, false);
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
    public boolean fightAnimFinished() {
        return ((!currentMonsterAnim.equals("Attack")) && !currentHeroAnim.equals("Attack")) || ((currentMonsterAnim.equals("Attack") && monsters.get(monsterType).get(currentMonsterAnim).isAnimationFinished(stateTimeMonster))
                && (currentHeroAnim.equals("Attack") && heroAnim.get(currentHeroAnim).isAnimationFinished(stateTimeHero)));
    }
}
