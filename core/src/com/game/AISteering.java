package com.game;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class AISteering{
    MapRender world;
    MyScreen hero;
    Npc npc;

    public AISteering(){
        super();

        world = Spiel.INSTANCE.getMyScreen().getMap();
        hero = Spiel.INSTANCE.getMyScreen();

        Arrive<Vector2> arriveSB = new Arrive<Vector2>(npc, hero).setTimeToTarget(0.01f).setArrivalTolerance(2f).setDecelerationRadius(3);
    }
}
