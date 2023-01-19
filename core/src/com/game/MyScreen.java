package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private Animator animator;
    private MapRender map;
    private Body player;
    private PlayerMap pm;
    private Body[][] body;
    private boolean Interior;


    public MyScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    public void create() {
        Interior = false;
        pm = new PlayerMap();
        animator = new Animator();
        map = new MapRender(batch);
        player = createPlayer();


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

        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            Spiel.INSTANCE.shopScreen();

        }
        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
        player.setLinearVelocity(verticalForce * 5, player.getLinearVelocity().x);
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

    public MapRender getMap() {
        return map;

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

}