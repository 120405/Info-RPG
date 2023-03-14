package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public class PlayerMap implements Steerable<Vector2> {
    private final Body body;


    boolean tagged;
    float maxLinearSpeed, maxLinearAcceleration;
    float maxAngularSpeed, maxAngularAcceleration;
    float bR;

    public PlayerMap(World world) {
        body = createPlayer(world);
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
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = 1f;
        //fixDef.restitution = 1f;
        fixDef.isSensor = true;
        Fixture fixture = pBody.createFixture(fixDef);
        //pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;

    }

    public void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            System.out.println(body.getPosition().x);
            System.out.println(body.getPosition().y);
        }

        int horizontalForce = 0;
        int verticalForce = 0;
        float speed = (float) (1.5f /*- (Spiel.INSTANCE.getFight().getHero().getWeight() / 6)*/);
        if ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))) {
            speed = 8f;
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
        body.setLinearVelocity(horizontalForce * 5, body.getLinearVelocity().y);
        body.setLinearVelocity(verticalForce * 5, body.getLinearVelocity().x);


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
        return body;
    }

    public float getXPos() {
        return body.getPosition().x;
    }

    public float getYPos() {
        return body.getPosition().y;
    }

    public void setPos(float x, float y) {
        body.setTransform(x, y, 0);
    }

    @Override
    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    @Override
    public float getBoundingRadius() {
        return bR;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
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
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxAngularAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public float getOrientation() {
        return body.getAngle();
    }

    @Override
    public void setOrientation(float orientation) {

    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return SteeringUtils.vectorToAngle(vector);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return SteeringUtils.angleToVector(outVector, angle);
    }

    @Override
    public Location<Vector2> newLocation() {
        return null;
    }

    public Vector2 newVector() {
        return new Vector2();
    }
}
