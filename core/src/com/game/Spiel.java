package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Spiel extends Game {
    public static Spiel INSTANCE;
    public Fight fight;
    private int money;
    private  TitleScreen title;
    private MyScreen game;
    private Shop shop;
    private Inventory inventory;
    private SpriteBatch batch;

    public Spiel() {
            INSTANCE = this;
            money = 10;
            inventory = new Inventory();
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

    public Inventory getInventory(){
        return inventory;
    }

    public void create() {
        fight = new Fight(60, 60, 20, "", 100, 100, 20, "");
        batch = new SpriteBatch();
        title = new TitleScreen(batch);
        game = new MyScreen(batch);
        shop = new Shop(batch);
        setScreen(title);
    }
    public void gameScreen() {
   setScreen(game);
    }
    public void shopScreen() {
   setScreen(shop);
    }
    public void titleScreen() {
    setScreen(title);
    }

}
