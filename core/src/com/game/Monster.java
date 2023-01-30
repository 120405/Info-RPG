package com.game;


public class Monster {
   private int LP = 0;
   private int random = 0;
   private int fullLP = 0;
   private int ATK = 0;
   private String name = "";
   private boolean alive = true;
   private int attackModifier;
   private int damageModifier;
   private int effectDuration;
   private String currentEffect;

    public Monster(int LP, int fullLP, int ATK, String name) {
    this.LP = LP;
    this.fullLP = fullLP;
    this.ATK = ATK;
    this.name = name;
    attackModifier = 0;
    damageModifier = 0;
    effectDuration = 0;
    currentEffect = "";
    }

    public int attack() {
        return ATK + random + attackModifier;
    }

    public void getAttacked(int strength, String effect) {
        LP = LP - strength - damageModifier;
        if (LP < strength) {
            LP = 0;
            die();
        }
        if (effectDuration > 0) {
            effectDuration --;
        }
        if(effectDuration == 0){
            attackModifier = 0;
            damageModifier = 0;
            currentEffect = "";
        }

        if(!effect.equals("")) {
            currentEffect = effect;
        }
        if(currentEffect.equals("fire")){
            effectDuration = 5;
            damageModifier = 20;
        }
        if(currentEffect.equals("poison")){
            effectDuration = 5;
            damageModifier = 5;
            attackModifier = -5;
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

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }
}
