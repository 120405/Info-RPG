package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class ImageDistributor {
    public ImageDistributor() {

    }
    public HashMap<Integer, TextureRegion[]> getSwordImages() {
        Texture t =  new Texture(Gdx.files.internal("Items/weapons.png"));
        HashMap<Integer, TextureRegion[]> weaponMap = new HashMap<>();
        TextureRegion[][] tr = TextureRegion.split(t, t.getWidth()/8, t.getHeight()/9);
        for(int i = 0; i < 7; i++) {
            weaponMap.put(i, tr[i]);
        }
        return weaponMap;
    }


}
