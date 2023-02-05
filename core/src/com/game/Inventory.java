package com.game;

public class Inventory {
    private final Item sword;
    private final Item shield;
    private final Item dagger;
    private final Item armor;
    private Effect fire;
    private Effect poison;
    private Effect blank;
    private  Effect ice;

    public Inventory(){
        fire = new Effect("Fire", 0, 100, 2);
        poison = new Effect("Poison", -5, 10, 5);
        ice = new Effect("Ice",-10, 5, 5);
        blank = new Effect("",0,0,0);
        sword = new Item("", "Sword", 100, 10, 0, 50, 3, fire, "");
        shield = new Item("", "Shield", 100, 0, 10, 80,5, blank, "");
        dagger = new Item("", "Dagger", 200, 15, 0, 100,1, poison, "doubleAttack");
        armor = new Item("", "Armor", 100, 0, 20, 300, 10, blank, "");
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