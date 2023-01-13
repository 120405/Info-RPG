package com.game;

import com.badlogic.gdx.Game;

public class Spiel extends Game {
    public static Spiel INSTANCE;
    public int money;

    public Spiel() {
        if (INSTANCE == null) {
            INSTANCE = this;
        }

        money = 10;
    }

    public int getMoney(){
        return money;
    }

    public void moneyUp(int x){
        money = money + x;
    }

    public void moneyDown(int x){
        money = money - x;
    }

    public void create() {
        setScreen(new TitleScreen());
    }
}