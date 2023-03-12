package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Inventory {
    private final GUI_Item sword;
    private final GUI_Item baguette;
    private final GUI_Item shield;
    private final GUI_Item dagger;
    private final GUI_Item armor;
    private Effect fire;
    private Effect poison;
    private Effect blank;
    private  Effect ice;

    public Inventory(){
        fire = new Effect("Fire", 0, 100, 2);
        poison = new Effect("Poison", -5, 10, 5);
        ice = new Effect("Ice",-10, 5, 5);
        blank = new Effect("",0,0,0);
        sword = new GUI_Item("Sword", 100, 10, 0, 50, 3, fire, "", new Image(new Texture("crystal.png")), false);
        baguette = new GUI_Item("Baguette", 10000, 100000, 1000, 3000, 1, blank, "", new Image(new Texture("crystal.png")), false);
        shield = new GUI_Item("Shield", 100, 0, 10, 80,5, blank, "", new Image(new Texture("crystal.png")), false);
        dagger = new GUI_Item("Dagger", 200, 15, 0, 100,1, poison, "doubleAttack", new Image(new Texture("crystal.png")), false);
        armor = new GUI_Item("Armor", 100, 0, 20, 300, 10, blank, "", new Image(new Texture("crystal.png")), false);
    }

    public GUI_Item getSword() {
        return sword;
    }

    public GUI_Item getShield() {
        return shield;
    }

    public GUI_Item getDagger() {
        return dagger;
    }

    public GUI_Item getArmor() {
        return armor;
    }
}