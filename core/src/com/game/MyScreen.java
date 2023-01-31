package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyScreen extends ScreenAdapter implements Steerable<Vector2> {
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
    private MapInteraction mi;
    private MapInteraction[] InteractionArray;
    private Music music = Gdx.audio.newMusic(Gdx.files.internal("Beginning.mp3"));
    private Music music2 = Gdx.audio.newMusic(Gdx.files.internal("Below the Surface.mp3"));
    //float delta;

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
        viewport = new FitViewport(Gdx.graphics.getWidth() / 60f, Gdx.graphics.getHeight() / 60f);
        stage = new Stage(viewport);
        player = createPlayer();
        music.setVolume(0.3f);
        music.play();
        music2.setVolume(0.3f);

        body = map.b2dPlats(MapRender.layer1);
        createContactsOverworld();
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        handleInput();
        cameraUpdate(delta);
        map.renderBackground();
        animator.render();
        map.renderForeground();
        //mapCheck();
        map.world.step(1 / 60f, 6, 2);
        enterCheck();
        //musicUpdate();

    }

    private void createContactsOverworld() {
        InteractionArray = new MapInteraction[1];
        InteractionArray[0] = new MapInteraction(81, 92, map.world, 55, 75);
    }

    private void createContactsInterior() {
        InteractionArray = new MapInteraction[1];
        InteractionArray[0] = new MapInteraction(55, 75, map.world, 81, 91);
    }

    private void deleteContacts() {
        for (int i = 0; i < InteractionArray.length; i++) {
            map.world.destroyBody(InteractionArray[i].pBody);
            InteractionArray[i] = null;
        }
    }

    public void musicUpdate() {
        if (!getInterior() && !music.isPlaying()) {
            music.play();
        }

    }

    public void musicFadeOut(final Music music) {
        float delay = 0.2f;
        for (float i = (music.getVolume() * 10); i > 0; i -= music.getVolume()) {

            // seconds

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    music.setVolume(music.getVolume() - music.getVolume() / 3);

                }
            }, delay);
            delay = delay + 0.2f;

        }
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                music.stop();
            }
        }, 2.6f);


    }

    public void musicFadeIn(final Music music) {
        final float goal = music.getVolume();
        music.setVolume(goal / 4);
        music.play();
        float delay = 0.2f;
        for (float i = (goal * 10); i > 0; i -= goal) {

            // seconds

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    music.setVolume(music.getVolume() + music.getVolume() / 8);

                }
            }, delay);
            delay = delay + 0.2f;
        }
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                music.setVolume(goal);
            }
        }, 2.6f);


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
            deleteContacts();
            createContactsInterior();
            Interior = true;
        } else {
            map.NoC(body, MapRender.layer2);
            body = map.b2dPlats(MapRender.layer1);
            deleteContacts();
            createContactsOverworld();
            Interior = false;
        }
    }

    public void enterCheck() {
        for (int i = 0; i < InteractionArray.length; i++) {
            if (InteractionArray[i].isEntrance() && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setTransform(InteractionArray[i].getTargetX(), InteractionArray[i].getTargetY(), 0);
                switchMap();
            }
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            System.out.println(music.getVolume());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            switchMusic(music, music2);
        }
    }


    public boolean getInterior() {
        return Interior;
    }

    public void switchMusic(final Music m1, final Music m2){
        musicFadeOut(m1);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                musicFadeIn(m2);
            }
        }, 2.6f);
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

    public MapInteraction[] getInteractionArray() {
        return InteractionArray;
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

    public Vector2 newVector() {
        return new Vector2();
    }
}