package com.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {
    SpriteBatch batch;
    Texture knightWalk;
    float stateTime;
    Animation<TextureRegion> RunUpAnim;
    Animation<TextureRegion> RunDownAnim;
    Animation<TextureRegion> RunLeftAnim;
    Animation<TextureRegion> RunRightAnim;
    TextureRegion currentFrame;


    Sprite knight;


    public Animator() {
        create();
    }


    public void create() {
        batch = new SpriteBatch();
        stateTime = 0f;

        knightWalk = new Texture(Gdx.files.internal("Soldier Walk-Sheet.png"));
        RunDownAnim = CreateAnimRow(knightWalk, 5, 6,0.2f,0);
        RunUpAnim = CreateAnimRow(knightWalk, 5, 6,0.2f,1);
        RunRightAnim = CreateAnimRow(knightWalk, 5, 6,0.2f,2);
        RunLeftAnim = CreateAnimRow(knightWalk, 5, 6,0.2f,3);
    }



    public Animation<TextureRegion> CreateAnim(Texture t, int row, int col,float frameTime) {
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

    public Animation<TextureRegion> CreateAnimRow(Texture t, int row, int col,float frameTime,int col1) {
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
    public void moveUpdate(){
         currentFrame = RunUpAnim.getKeyFrame(stateTime, true);
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
           currentFrame = RunLeftAnim.getKeyFrame(stateTime, true);
        }
       else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            currentFrame = RunRightAnim.getKeyFrame(stateTime, true);
        }
       else if(Gdx.input.isKeyPressed(Input.Keys.W)){
            currentFrame = RunUpAnim.getKeyFrame(stateTime, true);
        }
       else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            currentFrame = RunDownAnim.getKeyFrame(stateTime, true);
        }
        else {
            TextureRegion[][] tmp = TextureRegion.split(knightWalk, knightWalk.getWidth() / 6, knightWalk.getHeight() / 5);
            currentFrame = tmp[4][0];
        }
    }


    @Override
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        moveUpdate();
        knight = new Sprite(currentFrame);
        knight.setCenter(currentFrame.getRegionWidth()/2f,currentFrame.getRegionHeight()/2f);
        knight.setOrigin(currentFrame.getRegionWidth()/2f,currentFrame.getRegionHeight()/2f);
        knight.setPosition(Gdx.graphics.getWidth()/2f-15f,Gdx.graphics.getHeight()/2f+30f);
        knight.scale(3.3f);


        batch.begin();

        knight.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        knightWalk.dispose();
    }
}


