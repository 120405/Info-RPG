package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Compass {
    private float targetX;
    private Texture CompassNeedleTexture;
    private Texture CompassBaseTexture;
    private Sprite CompassNeedle;
    private Sprite CompassBase;
    private float targetY;

    public Compass(float x, float y) {
        targetX = x;
        targetY = y;
        CompassBaseTexture = new Texture(Gdx.files.internal("windrose.png"));
        CompassNeedleTexture = new Texture(Gdx.files.internal("agulha.png"));
        CompassBase = new Sprite(CompassBaseTexture);
        CompassNeedle = new Sprite(CompassNeedleTexture);
        CompassBase.setCenter(CompassBase.getWidth() / 2, CompassBase.getHeight() / 2);
        CompassNeedle.setCenter(CompassNeedle.getWidth() / 2, CompassNeedle.getHeight() / 2);
        CompassBase.setOriginCenter();

        CompassBase.setSize(300f, 300f);
        CompassNeedle.setSize(50f, 150f);
        CompassNeedle.setOrigin(CompassNeedle.getWidth() / 2, 20f);

    }

    public float getRotation() {
        float radians = (float) Math.atan2(targetY - Spiel.INSTANCE.getMyScreen().getPlayer().getYPos(), targetX - Spiel.INSTANCE.getMyScreen().getPlayer().getXPos());
        System.out.println((float) Math.toDegrees(radians));
        return ((float) Math.toDegrees(radians) - 90);
    }

    public void setTarget(float x, float y) {
        targetX = x;
        targetY = y;
    }

    public void updateCompass() {
        float x = Spiel.INSTANCE.getMyScreen().getMap().getCam().position.x;
        float y = Spiel.INSTANCE.getMyScreen().getMap().getCam().position.y;
        //CompassBase.setPosition(knight.getX()+5, knight.getY()+5 );
        // CompassNeedle.setPosition(knight.getX()+6.9f, knight.getY()+7f );
        CompassBase.setPosition(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 1.5f);
        CompassNeedle.setPosition(Gdx.graphics.getWidth() / 20 + 120f, Gdx.graphics.getHeight() / 1.5f + 130f);
        CompassNeedle.setRotation(getRotation());
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        CompassBase.draw(batch);
        CompassNeedle.draw(batch);
        batch.end();
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }
}
