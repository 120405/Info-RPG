package com.game;

import com.badlogic.gdx.physics.box2d.*;

public class MapInteraction {
    public MapInteraction(int x,int y,World world){
        createBox(x,y,world);
    }
    public void createBox(int x, int y, World world){
        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(80, 80);
        def.fixedRotation = true;
        pBody = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.9F, 0.9f);
        //pBody.createFixture(shape, 1.0f);
        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.friction = 1f;
        fixDef.restitution = 1f;
        Fixture fixture = pBody.createFixture(fixDef);
        //fixDef.isSensor = true;
        shape.dispose();


    }

}
