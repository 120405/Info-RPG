package com.game;

public class Effect {

    private final String name;
    private final int attackModifier;
    private final int damageModifier;
    private final int effectDuration;
    public Effect(String name, int aM, int dM, int eD){
        this.name = name;
        this.attackModifier = aM;
        this.damageModifier = dM;
        this.effectDuration = eD;
    }

    public String getName() {
        return name;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public int getEffectDuration() {
        return effectDuration;
    }
}
