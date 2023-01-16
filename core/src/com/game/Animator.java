package com.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {
    SpriteBatch batch;
    Texture SRun;
    float stateTime;
    Animation<TextureRegion> SRunAnim;


    public Animator() {
        create();
    }


    public void create() {
        batch = new SpriteBatch();
        stateTime = 0f;

        SRun = new Texture(Gdx.files.internal("SRun.png"));
        SRunAnim = CreateAnim(SRun, 3, 2,0.5f);
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


    @Override
    public void render() {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = SRunAnim.getKeyFrame(stateTime, true);
        batch.begin();
        batch.draw(currentFrame,Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()/2);

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
        SRun.dispose();
    }
}


