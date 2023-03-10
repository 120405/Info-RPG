package com.game;

import java.util.*;

public class Monster {
   private int LP;
   private int random = 0;
   private int fullLP;
   private int ATK;
   private String name;
   private int type;
   private boolean alive = true;
   private int attackModifier;
   private int damageModifier;
   private int effectDuration;
   private Effect currentEffect;
   private Effect blank;
   private Random rr;
   private GUI_Item weapon;
   private GUI_Item shield;
   private GUI_Item armor;
   private List <Effect> effects;

    public Monster(int LP, int fullLP, int ATK, String name, int type) {
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
        createList();
    }

    public void createList(){
        effects = new List<Effect>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Effect> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Effect effect) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Effect> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Effect> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Effect get(int index) {
                return null;
            }

            @Override
            public Effect set(int index, Effect element) {
                return null;
            }

            @Override
            public void add(int index, Effect element) {

            }

            @Override
            public Effect remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Effect> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Effect> listIterator(int index) {
                return null;
            }

            @Override
            public List<Effect> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
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

        if(!effect.getName().equals("")){
            effects.add(effect);
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
            getAttacked(strength, blank, "");
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
