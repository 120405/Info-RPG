package com.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Inventory {
    private Item sword;
    private Item shield;
    private Item dagger;

    public Inventory(){
        sword = new Item(new Image(new Texture("crystal.png")), "Sword", 100, 20, 0, 50, "");
        shield = new Item(new Image(new Texture("crystal.png")), "Shield", 100, 0, 20, 80, "");
        dagger = new Item(new Image(new Texture("crystal.png")), "Dagger", 200, 15, 0, 100, "");
    }

    public Item getSword() {
        return sword;
    }

    public Item getShield() {
        return shield;
    }

    public Item getDagger() {
        return dagger;
    }
    public Item getItem(String name) {
    	Item item = null;
		return item;
    	
    }
}