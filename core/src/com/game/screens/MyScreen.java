package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.Animator;
import com.game.Buttons;
import com.game.MapRender;
import com.game.PlayerMap;
import com.game.Spiel;
import com.game.ai.Npc;
import com.game.ai.SteeringUtils;

public class MyScreen extends ScreenAdapter implements Steerable<Vector2>{
    private final SpriteBatch batch;
    private Animator animator;
    private MapRender map;
    private Body player;
    private Npc npc;
    private PlayerMap pm;
    private Body[][] body;
    private boolean Interior;
    float bR = 0;
    private Viewport viewport;
    private boolean tagged;
    private Stage stage;


    float maxLinearSpeed, maxLinearAcceleration;
    float maxAngularSpeed, maxAngularAcceleration;


    public MyScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    public void create() {
        Interior = false;
        pm = new PlayerMap();
        animator = new Animator();
        map = new MapRender(batch);
        viewport = new FitViewport(Gdx.graphics.getWidth()/60f,Gdx.graphics.getHeight()/60f);
     // stage = new Stage(viewport);
        stage = new Stage();
        player = createPlayer();

        //npc = createNpc();


        body = map.b2dPlats(MapRender.layer1);
        
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        handleInput();
        cameraUpdate(delta);

        map.renderBackground();
        animator.render();
        map.renderForeground();
        mapCheck();
        map.world.step(1 / 60f, 6, 2);
        stage.act(delta);
        stage.draw();
        Spiel.INSTANCE.createHealthBars(false);
    }

    public Body createPlayer() {

        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(80, 80);
        def.fixedRotation = true;
        pBody = map.world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.9F, 0.9f);
        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;

    }

    /*public Body createNpc() {

        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(82, 80);
        def.fixedRotation = true;
        pBody = map.world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.9F, 0.9f);
        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;

    }*/

    public void switchMap() {
        if (!Interior) {
            map.NoC(body, MapRender.layer1);
            body = map.b2dPlats(MapRender.layer2);
            Interior = true;
        } else {
            map.NoC(body, MapRender.layer2);
            body = map.b2dPlats(MapRender.layer1);
            Interior = false;
        }
    }

    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {

            switchMap();

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            System.out.println(player.getPosition().x);
            System.out.println(player.getPosition().y);
        }

        int horizontalForce = 0;
        int verticalForce = 0;
        float speed = 1.5f;
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
        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
        player.setLinearVelocity(verticalForce * 5, player.getLinearVelocity().x);



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

    public void mapCheck() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if (!Interior) {


                if ((player.getPosition().x > 80 && player.getPosition().x < 82) && (player.getPosition().y > 90 && player.getPosition().y < 92)) {

                    switchMap();
                    player.setTransform(55, 75, 0);
                }

            } else {
                if ((player.getPosition().x > 54 && player.getPosition().x < 56) && (player.getPosition().y > 74 && player.getPosition().y < 76)) {
                    switchMap();
                    player.setTransform(81, 91, 0);
                }

            }
        }
    }


    public boolean getInterior() {
        return Interior;
    }



    public void cameraUpdate(float delta) {
        Camera cam = map.getCam();
        Vector3 position = cam.position;
        position.x = player.getPosition().x;
        position.y = player.getPosition().y;

        cam.position.set(position);

        cam.update();
    }

    public void dispose() {
        batch.dispose();
        map.dispose();
        animator.dispose();
    }

    public Body getPlayer() {
        return player;
    }

    public MapRender getMap() {
        return map;
    }

    @Override
    public Vector2 getLinearVelocity() {
        return player.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return player.getAngularVelocity();
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
        return player.getPosition();
    }

    @Override
    public float getOrientation() {
        return player.getAngle();
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

    public Vector2 newVector(){
        return  new Vector2();
    }
    public void show() {
    	new Buttons("Inventory", stage, "showInv", 16, 3, Color.OLIVE);
    	 stage.addActor(Spiel.INSTANCE.getInventoryGUI().getInventory());
    }
    public void hide() {
    	stage.clear();
    }
    
}