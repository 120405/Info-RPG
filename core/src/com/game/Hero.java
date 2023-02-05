package com.game;

import com.badlogic.gdx.math.*;
import java.util.Random;

public class Hero {
    private int LP = 0;
    private int random = 0;
    private int fullLP = 0;
    private int ATK = 0;
    private Item weapon;
    private Item shield;
    private Item armor;
    private String name = "";
    private boolean alive = true;
    private Random rr;
    private int attackModifier;
    private int damageModifier;
    private int effectDuration;
    private String currentEffect;

    public Hero(int LP, int fullLP, int ATK, String name) {
        this.LP = LP;
        this.fullLP = fullLP;
        this.ATK = ATK;
        this.name = name;
        rr = new Random();
    }


    public int attack() {
    	int a =(int) (Math.random()* 100);
    	if(a <= 70) {
        if(weapon == null) {
            return ATK + random;
        }else{
            return ATK + random + weapon.getAtk();
        }
    	}else {return 0;
    	}
    }

    public String getEffectRd(){
    	int a =(int) (Math.random()* 100);
        if(weapon == null) {
            return "";
        }else{
            if(a <=60) {
                return weapon.getEffect();
            }else{
            return "";
                }
            }
        }

    public String getSkillRd(){
        int a =(int) (Math.random()* 100);
        if(weapon == null) {
            return "";
        }else{
            if(a <=60) {
                return weapon.getSkill();
            }else{
                return "";
            }
        }
    }

    public void getAttacked(int strength, String effect, String weaponSkill) {
        int def = 0;
        if (armor != null){
            def = def + armor.getDef();
        }
        if (shield != null){
            def = def + shield.getDef();
        }
        LP = LP - strength - damageModifier + def;
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

        if(!effect.equals("") || strength != 0) {
            currentEffect = effect;
        }
        if(currentEffect.equals("fire")){
            if (strength != 0) {
        	effectDuration = 5;
            }
            damageModifier = 100;
        }
        if(currentEffect.equals("poison")){
        	if (strength != 0) {
            effectDuration = 5;
        	}
            damageModifier = 5;
            attackModifier = -5;
        }

        if (weaponSkill.equals("doubleAttack")){
            getAttacked(strength, effect, "");
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

    public void setWeapon(Item x){
        weapon = x;
    }

    public Item getWeapon() {
        return weapon;
    }

    public void setShield(Item x){
        shield = x;
    }

    public Item getShield() {
        return shield;
    }
}
