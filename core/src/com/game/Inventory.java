package com.game;

public class Inventory {
    private final Item sword;
    private final Item shield;
    private final Item dagger;

    public Inventory(){
        sword = new Item("", "Sword", 100, 10, 0, 50, "fire", "");
        shield = new Item("", "Shield", 100, 0, 20, 80, "", "");
        dagger = new Item("", "Dagger", 200, 15, 0, 100, "", "");
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
}