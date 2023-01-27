package com.game;

import com.badlogic.gdx.physics.box2d.*;

public class ListenerClass implements ContactListener {

private MapInteraction[] mapInteractions;
    public ListenerClass() {

    }

    @Override
    public void beginContact(Contact contact) {
        mapInteractions = Spiel.INSTANCE.getMyScreen().getInteractionArray();
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if(fa.getBody().getType() != BodyDef.BodyType.StaticBody && fb.getBody().getType() != BodyDef.BodyType.StaticBody) {

             for(int i = 0;i < mapInteractions.length; i++){
                 if (contact.getFixtureA() == mapInteractions[i].getFixture() || contact.getFixtureB() == mapInteractions[i].getFixture()){
                     mapInteractions[i].setEntrance(true);
                     System.out.println("Alarm");
                 }
             }
        }
    }

    @Override
    public void endContact(Contact contact) {
        for(int i = 0;i < mapInteractions.length; i++){
            mapInteractions[i].setEntrance(false);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
