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
    monsters.put(2,new HashMap<String, Animation<TextureRegion>>());
    monsters.put(3,new HashMap<String, Animation<TextureRegion>>());
    createAnimationMaps();

    }

    @Override
    public void resize(int width, int height) {

    }
    public void createAnimationMaps() {
        heroAnim.put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Attack1.png")), 1, 5, 0.2f, 0));
        heroAnim.put("Idle", createAnim(new Texture(Gdx.files.internal("FightAnimations/Hero/Idle.png")), 1, 4, 0.2f));
        heroAnim.put("Dead",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Dead.png")), 1, 6, 0.2f, 0));
        heroAnim.put("Hurt",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Hurt.png")), 1, 2, 0.2f, 0));
        heroAnim.put("Walk",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Hero/Walk.png")), 1, 8, 0.2f, 0));

        monsters.get(1).put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster1/Attack1.png")), 1, 5, 0.2f, 0));
        monsters.get(1).put("Idle",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster1/Idle.png")), 1, 4, 0.2f, 0));
        monsters.get(1).put("Dead",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster1/Dead.png")), 1, 6, 0.2f, 0));
        monsters.get(1).put("Hurt",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster1/Hurt.png")), 1, 2, 0.2f, 0));

        monsters.get(2).put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster2/Attack1.png")), 1, 4, 0.2f, 0));
        monsters.get(2).put("Idle",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster2/Idle.png")), 1, 7, 0.2f, 0));
        monsters.get(2).put("Dead",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster2/Dead.png")), 1, 5, 0.2f, 0));
        monsters.get(2).put("Hurt",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster2/Hurt.png")), 1, 3, 0.2f, 0));

        monsters.get(3).put("Attack",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster3/Attack1.png")), 1, 5, 0.2f, 0));
        monsters.get(3).put("Idle",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster3/Idle.png")), 1, 7, 0.2f, 0));
        monsters.get(3).put("Dead",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster3/Dead.png")), 1, 4, 0.2f, 0));
        monsters.get(3).put("Hurt",createAnimRow(new Texture(Gdx.files.internal("FightAnimations/Monster3/Hurt.png")), 1, 2, 0.2f, 0));
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
                if(currentHeroAnim.equals("Hurt")) {
                    currentHeroAnim = "Idle";
                }
                if (currentHeroAnim.equals("Attack")) {
                    currentHeroAnim = "Idle";
                    if(!currentMonsterAnim.equals("Dead")) {
                        if(Spiel.INSTANCE.getFightScreen().getMonsterDMG()) {
                            currentMonsterAnim = "Hurt";
                            stateTimeMonster = 0f;
                        } else {
                            currentMonsterAnim = "Attack";
                            stateTimeMonster = 0f;
                        }


                    }


                }
            }
        }

        heroSprite = new Sprite(heroAnim.get(currentHeroAnim).getKeyFrame(stateTimeHero, false));

        if(!currentMonsterAnim.equals("Dead")) {
            if(monsters.get(monsterType).get(currentMonsterAnim).isAnimationFinished(stateTimeMonster)) {
                stateTimeMonster = 0f;
                if(currentMonsterAnim.equals("Hurt")) {
                    currentMonsterAnim = "Attack";
                } else if(currentMonsterAnim.equals("Attack")) {
                    currentMonsterAnim = "Idle";
                    if(Spiel.INSTANCE.getFightScreen().getHeroDMG()) {
                        currentHeroAnim = "Hurt";
                    }

                    stateTimeHero = 0f;
                    if(Spiel.INSTANCE.fight.getWinner().equals("Monster")) {
                        currentHeroAnim = "Dead";
                    }

                }
            }
        }
        if(currentMonsterAnim == "Dead") {
            if(currentHeroAnim != "Attack") {
                monsterSprite = new Sprite(monsters.get(monsterType).get(currentMonsterAnim).getKeyFrame(stateTimeMonster, false));
            } else {
                monsterSprite = new Sprite(monsters.get(monsterType).get("Idle").getKeyFrame(stateTimeMonster, false));
            }

        } else {
            monsterSprite = new Sprite(monsters.get(monsterType).get(currentMonsterAnim).getKeyFrame(stateTimeMonster, false));
        }

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
        return ((!currentMonsterAnim.equals("Attack") && !currentMonsterAnim.equals("Hurt")) && (!currentHeroAnim.equals("Attack") && !currentHeroAnim.equals("Hurt"))) || ((currentMonsterAnim.equals("Attack") && monsters.get(monsterType).get(currentMonsterAnim).isAnimationFinished(stateTimeMonster))
                && (currentHeroAnim.equals("Attack") && heroAnim.get(currentHeroAnim).isAnimationFinished(stateTimeHero)));
    }
    public void setStateTimeHero(float time) {
        stateTimeHero = time;
    }
    public float getMonsterX() {
      return monsterSprite.getX();
    }
    public float getMonsterY() {
        return monsterSprite.getY()-190;
    }
    public float getMonsterWidth() {
        return monsterSprite.getX();
    }
    public float getMonsterHeight() {
        return monsterSprite.getTexture().getHeight()+70;
    }

}
