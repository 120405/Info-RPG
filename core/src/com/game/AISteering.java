package com.game;

import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class AISteering {
    MapRender world;
    PlayerMap hero;
    Npc npc;

    public AISteering() {
        super();

        world = Spiel.INSTANCE.getMyScreen().getMap();
        hero = Spiel.INSTANCE.getMyScreen().getPlayer();
        npc = Spiel.INSTANCE.getNpc();

        Arrive<Vector2> arriveSB = new Arrive<Vector2>(npc, hero).setTimeToTarget(0.01f).setArrivalTolerance(2f).setDecelerationRadius(20);
        npc.setBehavior(arriveSB);
    }
}
