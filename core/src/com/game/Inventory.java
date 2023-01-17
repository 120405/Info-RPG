package com.game;

public class Inventory {
    public Item sword;
    public Item shield;

    public Inventory(){
        sword = new Item("", "Sword", 100, 20, 5, 50, "");
        shield = new Item("", "Shield", 100, 5, 20, 80, "");
    }

    public Item getSword() {
        return sword;
    }

    public Item getShield() {
        return shield;
    }
}