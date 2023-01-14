package com.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class MapColider {
    static TiledMapTileLayer layer;
    static float tileSize;
    static ChainShape cs;

    // Methode, um automatisch die art der Texture zu erkennen und dementsprechende
    // Collisions zu erstellen
    public static void b2dPlats() {

        layer = (TiledMapTileLayer) MyScreen.tiledmap.getLayers().get(0);
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

                MyScreen.world.createBody(bdef).createFixture(fdef).setUserData("collision");

            }

        }
    }

}
