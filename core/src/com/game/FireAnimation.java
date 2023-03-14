package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FireAnimation {
    private Animation<TextureRegion> fireAnim;
    private Texture fireAnimTexture;
    private TextureRegion currentFrame;
    private Sprite fire;
    private float xPos;
    private float yPos;
    float stateTime;


    public FireAnimation(float x,float y){
        fireAnimTexture = new Texture(Gdx.files.internal("fire_fx_v1.0/png/orange/loops/burning_loop_2.png"));
        fireAnim = createAnim(fireAnimTexture,1,8,0.1f);
        xPos = x;
        yPos = y;

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
    public void render(){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = fireAnim.getKeyFrame(stateTime,true);
        fire = new Sprite(currentFrame);
        fire.setOrigin(0,0);
        fire.setPosition(xPos,yPos);
        fire.setSize(2.5f,2.5f);

    }
    public Sprite getFire(){
        return fire;
    }

}
