package com.game;

public class Inventory {
    private final Item sword;
    private final Item shield;
    private final Item dagger;
    private final Item armor;

    public Inventory(){
        sword = new Item("", "Sword", 100, 10, 0, 50, "fire", "");
        shield = new Item("", "Shield", 100, 0, 10, 80, "", "");
        dagger = new Item("", "Dagger", 200, 15, 0, 100, "", "doubleAttack");
        armor = new Item("", "Armor", 100, 0, 20, 300, "", "");
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

    public Item getArmor() {
        return armor;
    }
}