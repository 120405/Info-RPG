package com.game;

public class Hero {
    private int LP = 0;
    private int random = 0;
    private int fullLP = 0;
    private int ATK = 0;
    private String name = "";
    private boolean alive = true;

    public Hero(int LP, int fullLP, int ATK, String name) {
        this.LP = LP;
        this.fullLP = fullLP;
        this.ATK = ATK;
        this.name = name;
    }


    public int attack() {
        return ATK +  random;
    }

    public void getAttacked(int strength) {
        LP = LP - strength;
        if (LP < strength) {
            LP = 0;
            die();
        }
    }

    public void die() {
        alive = false;
    }

    public void setLP(int LP) {
        this.LP = LP;
    }

    public int getLP() {
        return LP;
    }

    public void setFullLP(int fullLP) {
        this.fullLP = fullLP;
    }

    public int getFullLP() {
        return fullLP;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getATK() {
        return ATK;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(boolean alive) {
        this.alive = alive;
    }

    public boolean getStatus() {
        return alive;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public int getRandom() {
        return random;
    }

}
