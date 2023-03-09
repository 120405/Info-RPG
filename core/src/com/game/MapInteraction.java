package com.game;

import com.badlogic.gdx.physics.box2d.*;

public class MapInteraction {
    private final int tX;
    private final int tY;
    private Fixture fixture;
    private boolean entrance;
    int x;
    int y;
    String destination;
    Body pBody;
    World world;
    public MapInteraction(int x,int y,World world,int targetX,int targetY,String destination){
        createBox(x,y,world);
        tX = targetX;
        tY = targetY;
        entrance = false;
        this.destination = destination;
    }

    public void createBox(int x, int y, World world){


        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x, y);
        def.fixedRotation = true;
        pBody = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.9F, 0.9f);
        //pBody.createFixture(shape, 1.0f);
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = 1f;
        //fixDef.restitution = 1f;
        fixDef.isSensor = true;
        fixture = pBody.createFixture(fixDef);
        //fixDef.isSensor = true;
        shape.dispose();


    }
    public void showBox(){
        createBox(x,y,world);
    }
    public void hideBox(){
        world.destroyBody(pBody);
    }
    public String getDestination(){
        return destination;
    }
    public int getTargetX(){
        return tX;
    }
    public int getTargetY(){
        return tY;
    }
    public Fixture getFixture() {
        return fixture;
    }

    public boolean isEntrance() {
        return entrance;
    }
    public void setEntrance(boolean b){
        entrance = b;
    }
}
