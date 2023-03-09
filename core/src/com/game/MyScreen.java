package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MyScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private Animator animator;
    private MapRender map;
    private PlayerMap player;
    private Body[][] body;
    private boolean Interior;
    private MapInteraction[] InteractionArray;
    private final Music music = Gdx.audio.newMusic(Gdx.files.internal("Beginning.mp3"));
    private final Music music2 = Gdx.audio.newMusic(Gdx.files.internal("Below the Surface.mp3"));
    private float volume;
    private FitViewport viewport;
    private Stage stage;

    public MyScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    public void create() {
        volume = 0.3f;
        Interior = false;
        animator = new Animator();
        map = new MapRender(batch);
        viewport = new FitViewport(Gdx.graphics.getWidth() / 60f, Gdx.graphics.getHeight() / 60f);
        stage = new Stage();

        player = new PlayerMap(getMap().world);
        music.setVolume(volume);
        music.play();
        music2.setVolume(volume);
        body = map.b2dPlats(MapRender.layer1);
        createContactsOverworld();
        show();
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        player.handleInput();
        cameraUpdate(delta);
        map.renderBackground();
        animator.render();
        map.renderForeground();
        map.world.step(1 / 60f, 6, 2);
        enterCheck();
        stage.act(delta);
        stage.draw();
        Spiel.INSTANCE.createHealthBars(false);
    }

    public void cameraUpdate(float delta) {
        Camera cam = map.getCam();
        Vector3 position = cam.position;
        position.x = player.getXPos();
        position.y = player.getYPos();

        cam.position.set(position);

        cam.update();
    }


    private void createContactsOverworld() {
        InteractionArray = new MapInteraction[1];
        InteractionArray[0] = new MapInteraction(81, 92, map.world, 55, 75,"cave");
    }

    private void createContactsInterior() {
        InteractionArray = new MapInteraction[1];
        InteractionArray[0] = new MapInteraction(55, 75, map.world, 81, 91,"world");
    }

    private void deleteContacts() {
        for (int i = 0; i < InteractionArray.length; i++) {
            map.world.destroyBody(InteractionArray[i].pBody);
            InteractionArray[i] = null;
        }
    }

    public void enterCheck() {
        for (int i = 0; i < InteractionArray.length; i++) {
            if (InteractionArray[i].isEntrance() && Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                player.setPos(InteractionArray[i].getTargetX(), InteractionArray[i].getTargetY());
                if (InteractionArray[i].getDestination().equals("world")){
                    switchMusic(getCurrentMusic(),music);
                }
                if (InteractionArray[i].getDestination().equals("cave")){
                    switchMusic(getCurrentMusic(),music2);
                }
                switchMap();
            }
        }
    }


    public void switchMusic(final Music m1, final Music m2) {
        musicFadeOut(m1);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                musicFadeIn(m2);
            }
        }, 2.6f);
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
                music.setVolume(volume);
            }
        }, 2.6f);
    }

    public void musicFadeIn(final Music music) {
        final float goal = volume;
        music.setVolume(goal / 4);
        music.play();
        float delay = 0.2f;
        for (float i = (goal * 10); i > 0; i -= goal) {


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


    public boolean getInterior() {
        return Interior;
    }

    public Music getCurrentMusic() {
        if (music.isPlaying()) {
            return music;
        } else if (music2.isPlaying()) {
            return music2;
        } else {
            return null;
        }
    }

    public MapInteraction[] getInteractionArray() {
        return InteractionArray;
    }


    public MapRender getMap() {
        return map;
    }

    public PlayerMap getPlayer() {
        return player;
    }


    public Vector2 newVector() {
        return new Vector2();
    }

    public void dispose() {
        batch.dispose();
        map.dispose();
        animator.dispose();
    }
    public void show() {
        new Buttons("Inventory", stage, "showInv", 16, 3, Color.OLIVE);
        stage.addActor(Spiel.INSTANCE.getInventory().getInventory());
    }
    public void hide() {
        stage.clear();
    }
}

