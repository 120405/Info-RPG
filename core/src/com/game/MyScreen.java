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


    public MyScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    public void create() {
        pm = new PlayerMap();
        animator = new Animator();
       map = new MapRender(batch);
       player = createPlayer();

        map.b2dPlats();
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        handleInput();
        cameraUpdate(delta);

        animator.render();
        map.render();

        map.world.step(1/60f, 6, 2);

    }
    public Body createPlayer() {

        Body pBody;
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(0, 0);
        def.fixedRotation = true;
        pBody = map.world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 1);

        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;

    }
    public void handleInput() {

        int horizontalForce = 0;
        int verticalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            horizontalForce -= 3;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            horizontalForce += 3;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            verticalForce -= 3;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            verticalForce += 3;

        }
        player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
        player.setLinearVelocity(verticalForce * 5, player.getLinearVelocity().x);
    }

    public MapRender getMap(){
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