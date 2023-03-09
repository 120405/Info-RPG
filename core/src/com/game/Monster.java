package com.game;

import java.util.List;
import java.util.Random;

public class Monster {
   private int LP = 0;
   private int random = 0;
   private int fullLP = 0;
   private int ATK = 0;
   private String name = "";
   private String type;
   private boolean alive = true;
   private int attackModifier;
   private int damageModifier;
   private int effectDuration;
   private Effect currentEffect;
   private Effect blank;
   private Random rr;
   private Item weapon;
   private Item shield;
   private Item armor;
   private List <Effect> effects;

    public Monster(int LP, int fullLP, int ATK, String name, String type) {
    this.LP = LP;
    this.fullLP = fullLP;
    this.ATK = ATK;
    this.name = name;
    this.type = type;
    attackModifier = 0;
    damageModifier = 0;
    effectDuration = 0;
    blank = new Effect("",0,0,0);
    currentEffect = blank;
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
    
    public Effect getEffectRd(){
    	int a =(int) (Math.random()* 100);
       if(weapon == null) {
           return blank;
       }else{
           if(a <=60) {
               return weapon.getEffect();
           }else{
           return blank;
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

    public void getAttacked(int strength, Effect effect, String weaponSkill) {
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
        
        if(!effects.isEmpty()) {
         for(int i = 0; i < effects.size(); i++) {
         
         currentEffect = effects.get(i);
         currentEffect.decreaseEffectDuration();
         
         if(currentEffect.getEffectDuration()== 0){
             currentEffect = blank;
             damageModifier = currentEffect.getDamageModifier();
             attackModifier = currentEffect.getAttackModifier();
             effects.remove(i);
             i--;
         }
         currentEffect = effect;
         if (strength != 0) {
             effectDuration = currentEffect.getEffectDuration();
             damageModifier = currentEffect.getDamageModifier();
             attackModifier = currentEffect.getAttackModifier();
            }
          }
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

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
