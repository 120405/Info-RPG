package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Compass {
    private float targetX;
    private float targetY;
    public Compass(float x,float y){
        targetX = x;
        targetY = y;
    }
    public float getRotation() {
        float radians = (float)Math.atan2(targetY - Spiel.INSTANCE.getMyScreen().getPlayer().getYPos(), targetX - Spiel.INSTANCE.getMyScreen().getPlayer().getXPos());
        System.out.println((float) Math.toDegrees(radians));
        return (-(float) Math.toDegrees(radians)-90);
    }

    public void setTarget(float x,float y){
        targetX = x;
        targetY = y;
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }
}
