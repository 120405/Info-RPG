package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.HashMap;
import java.util.List;

public class MapRender {
    static TiledMapTileLayer layer1;
    static TiledMapTileLayer layer2;
    private TiledMapTileLayer layerFire;
    static float tileSize;
    static ChainShape cs;
    static TiledMap tiledmap1;
    static TiledMap tiledmap2;
    public World world;
    private OrthogonalTiledMapRenderer tmr;
    private OrthogonalTiledMapRenderer tmr2;
    private final OrthographicCamera cam;
    private final SpriteBatch batch;
    public Body b1[][];
    private final int[] foregroundOverworld = {5};
    private final int[] backgroundOverworld = {0, 1, 4};
    private final int[] backgroundOverworld2 = {4};
    private final int[] foregroundInterior = {3};
    private final int[] backgroundInterior = {0, 1, 2};
    private int[] water1 = {3};
    private int[] water2 = {2};
    private float elapsedSinceAnimation;
    private boolean waterframe;
    private HashMap<Integer, FireAnimation> fireMap;
    private float stateTime;


    Box2DDebugRenderer debugRenderer;


    // Methode, um automatisch die art der Texture zu erkennen und dementsprechende
    // Collisions zu erstellen
    public MapRender(SpriteBatch batch) {
        fireMap = new HashMap<>();
        waterframe = true;
        elapsedSinceAnimation = 0f;
        world = new World(new Vector2(0, 0), false);
        world.setContactListener(new ListenerClass());
        this.batch = new SpriteBatch();
        tiledmap1 = new TmxMapLoader().load("2 - Kopie.tmx");
        tiledmap2 = new TmxMapLoader().load("2.tmx");

        float unitScale = 1 / 8f;
        tmr = new OrthogonalTiledMapRenderer(tiledmap1, unitScale);
        tmr2 = new OrthogonalTiledMapRenderer(tiledmap2, unitScale);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(60, 60 * (h / w));

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 60f, Gdx.graphics.getHeight() / 60f);
        layer1 = (TiledMapTileLayer) tiledmap1.getLayers().get("Col");
        layer2 = (TiledMapTileLayer) tiledmap2.getLayers().get("Col");
        layerFire = (TiledMapTileLayer) tiledmap1.getLayers().get("fire");
        placeFire(layerFire);
        debugRenderer = new Box2DDebugRenderer();



    }

    public Body[][] b2dPlats(TiledMapTileLayer layer) {

        b1 = new Body[layer.getHeight()][layer.getWidth()];
        tileSize = layer.getTileWidth() / 8f;


        // layers durchgehen
        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if (cell == null) {
                    continue;
                }
                if (cell.getTile() == null) {
                    continue;
                }

                // body erstellen
                BodyDef bdef = new BodyDef();
                FixtureDef fdef = new FixtureDef();
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set((col + 0.5f) * tileSize, (row + 0.5f) * tileSize);

                // Koordinaten verbinden
                Vector2[] v = new Vector2[5];
                v[0] = new Vector2(-tileSize / 2f, -tileSize / 2f);
                v[1] = new Vector2(-tileSize / 2f, tileSize / 2f);
                v[2] = new Vector2(tileSize / 2f, tileSize / 2f);
                v[3] = new Vector2(tileSize / 2f, -tileSize / 2f);
                v[4] = new Vector2(-tileSize / 2f, -tileSize / 2f);
                cs = new ChainShape();
                cs.createChain(v);

                // body einstellungen
                fdef.friction = 0f;
                fdef.shape = cs;

                // world.createBody(bdef).createFixture(fdef).setUserData("collision");
                b1[row][col] = world.createBody(bdef);
                b1[row][col].createFixture(fdef).setUserData("collision");


            }

        }
        return b1;
    }

    public void placeFire(TiledMapTileLayer layer) {
        tileSize = layer.getTileWidth() / 8f;
        // layers durchgehen
        int i = 0;
        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if (cell == null) {
                    continue;
                }
                if (cell.getTile() == null) {
                    continue;
                }

                FireAnimation fire = new FireAnimation((col-0.15f) * tileSize, (row+0.45f) * tileSize);
                fireMap.put(i, fire);
                i++;
            }
        }
    }

    public void NoC(Body[][] b, TiledMapTileLayer layer) {
        tileSize = layer.getTileWidth() / 8f;

        // layers durchgehen
        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                TiledMapTileLayer.Cell cell = layer.getCell(col, row);

                if (cell == null) {
                    continue;
                }
                if (cell.getTile() == null) {
                    continue;
                }
                world.destroyBody(b[row][col]);
            }

        }

    }


    public void handleCamInput() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean scrolled(float amountX, float amountY) {
                if (amountY > 0) {
                    if (cam.zoom < 2) {
                        cam.zoom = cam.zoom + 0.1f;
                    }
                }
                if (amountY < 0) {
                    if (cam.zoom > 0.5) {
                        cam.zoom = cam.zoom - 0.1f;
                    }
                }
                return true;
            }
        });

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
            //If the LEFT Key is pressed, translate the camera -3 units in the X-Axis
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
            //If the RIGHT Key is pressed, translate the camera 3 units in the X-Axis
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (cam.zoom > 0.5f) {
                cam.zoom = cam.zoom - 0.1f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (cam.zoom < 2) {
                cam.zoom = cam.zoom + 0.1f;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            System.out.print(cam.zoom);
        }


        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

    public OrthographicCamera getCam() {
        return cam;

    }

    public void renderForeground() {

        //handleInput();
        batch.setProjectionMatrix(cam.combined);


        cam.update();
        if (!Spiel.INSTANCE.getMyScreen().getInterior()) {
            tmr.setView(cam);
            tmr.render(foregroundOverworld);
        } else {
            tmr2.setView(cam);
            tmr2.render(foregroundInterior);
        }
        for (int i = 0; Spiel.INSTANCE.getMyScreen().getMap().getFireMap().size()-1 >= i; i++) {

            Spiel.INSTANCE.getMyScreen().getMap().getFireMap().get(i).render();
            batch.begin();
            Spiel.INSTANCE.getMyScreen().getMap().getFireMap().get(i).getFire().draw(batch);
            batch.end();
        }

        // Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
        debugRenderer.render(world, cam.combined);


    }

    public void renderBackground() {

        cam.update();
        if (!Spiel.INSTANCE.getMyScreen().getInterior()) {

            tmr.setView(cam);
            tmr.render(backgroundOverworld);
            renderWater();
            tmr.render(backgroundOverworld2);


        } else {
            tmr2.setView(cam);
            tmr2.render(backgroundInterior);
        }
    }


    public void renderWater() {
        elapsedSinceAnimation += Gdx.graphics.getDeltaTime();

        if (elapsedSinceAnimation > 0.5f) {
            waterframe = !waterframe;
            elapsedSinceAnimation = 0.0f;
        }
        if (waterframe) {
            tmr.render(water1);
        } else {
            tmr.render(water2);
        }
    }

    public HashMap<Integer, FireAnimation> getFireMap() {
        return fireMap;
    }

    public void dispose() {
        tmr.dispose();
        batch.dispose();
    }
}
