package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class PlayerMap implements Steerable<Vector2> {
    private final Body playerBody;

    public PlayerMap(World world) {
        playerBody = createPlayer(world);
    }

    public Body createPlayer(World world) {

        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(80, 80);
        def.fixedRotation = true;
        pBody = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.9F, 0.9f);
        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;

    }

    public void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            System.out.println(playerBody.getPosition().x);
            System.out.println(playerBody.getPosition().y);
        }

        int horizontalForce = 0;
        int verticalForce = 0;
        float speed = (float) (1.5f /*- (Spiel.INSTANCE.getFight().getHero().getWeight() / 6)*/);
        if ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))) {
            speed = 2f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            horizontalForce -= speed;


        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            horizontalForce += speed;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            verticalForce -= speed;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            verticalForce += speed;

        }
        playerBody.setLinearVelocity(horizontalForce * 5, playerBody.getLinearVelocity().y);
        playerBody.setLinearVelocity(verticalForce * 5, playerBody.getLinearVelocity().x);


        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            Spiel.INSTANCE.shopScreen();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Spiel.INSTANCE.titleScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            Spiel.INSTANCE.fightScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            Spiel.INSTANCE.shopScreen();
        }

    }

    public Body getPlayerBody() {
        return playerBody;
    }

    public float getXPos() {
        return playerBody.getPosition().x;
    }

    public float getYPos() {
        return playerBody.getPosition().y;
    }

    public void setPos(float x, float y) {
        playerBody.setTransform(x, y, 0);
    }


    @Override
    public Vector2 getLinearVelocity() {
        return null;
    }

    @Override
    public float getAngularVelocity() {
        return 0;
    }

    @Override
    public float getBoundingRadius() {
        return 0;
    }

    @Override
    public boolean isTagged() {
        return false;
    }

    @Override
    public void setTagged(boolean tagged) {

    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return 0;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {

    }

    @Override
    public float getMaxLinearSpeed() {
        return 0;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {

    }

    @Override
    public float getMaxLinearAcceleration() {
        return 0;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {

    }

    @Override
    public float getMaxAngularSpeed() {
        return 0;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {

    }

    @Override
    public float getMaxAngularAcceleration() {
        return 0;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {

    }

    @Override
    public Vector2 getPosition() {
        return null;
    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public void setOrientation(float orientation) {

    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return 0;
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return null;
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }
}
