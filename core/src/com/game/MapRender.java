package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class MapRender {
    static TiledMapTileLayer layer;
    static float tileSize;
    static ChainShape cs;
    static TiledMap tiledmap;
    public static World world;
    private OrthogonalTiledMapRenderer tmr;
    private OrthographicCamera cam;
    private final SpriteBatch batch;


    // Methode, um automatisch die art der Texture zu erkennen und dementsprechende
    // Collisions zu erstellen
    public MapRender(SpriteBatch batch) {
        this.batch = new SpriteBatch();
        tiledmap = new TmxMapLoader().load("1.tmx");
        float unitScale = 1 / 16f;
        tmr = new OrthogonalTiledMapRenderer(tiledmap, unitScale);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
       cam = new OrthographicCamera(20, 20 * (h / w));
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    public static void b2dPlats() {

        layer = (TiledMapTileLayer) tiledmap.getLayers().get(0);
        tileSize = layer.getTileWidth() / 16f;


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

                world.createBody(bdef).createFixture(fdef).setUserData("collision");

            }

        }
    }

    private void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
            //If the LEFT Key is pressed, translate the camera -3 units in the X-Axis
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
            //If the RIGHT Key is pressed, translate the camera 3 units in the X-Axis
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
            //If the DOWN Key is pressed, translate the camera -3 units in the Y-Axis
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
            //If the UP Key is pressed, translate the camera 3 units in the Y-Axis
        }


        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

    public void render() {
        handleInput();
       batch.setProjectionMatrix(cam.combined);
        cam.update();
        tmr.setView(cam);
        tmr.render();
    }
    public void dispose() {
        tmr.dispose();
        batch.dispose();
    }
}
