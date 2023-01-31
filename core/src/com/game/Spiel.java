package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Spiel extends Game {
    public static Spiel INSTANCE;
    public Fight fight;
    private int money;
    private TitleScreen title;
    private MyScreen game;
    private Shop shop;
    private Inventory inventory;
    private FightScreen fightScreen;
    private Options options;
    private final String name;
    private Npc npc;

    public Spiel(String name) {
        this.name = name;
        INSTANCE = this;
        money = 1000;
    }

    public int getMoney() {
        return money;
    }

    public void moneyUp(int x) {
        money = money + x;
    }

    public void moneyDown(int x) {
        money = money - x;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void buyItem(Item item, String type) {
        if (type.equals("weapon")) {
            if (fight.getHero().getWeapon() != null) {
                moneyUp(fight.getHero().getWeapon().getWorth());
            }
            fight.getHero().setWeapon(item);
        }
        if (type.equals("shield")) {
            if (fight.getHero().getShield() != null) {
                moneyUp(fight.getHero().getShield().getWorth());
            }
            fight.getHero().setShield(item);
        }
    }

    public void create() {
        inventory = new Inventory();
        fight = new Fight(80, 80, 20, "Monster", 100, 100, 20, "Hero");
        SpriteBatch batch = new SpriteBatch();
        game = new MyScreen(batch);
        npc = new Npc(1f);
        //player= new Player();

        shop = new Shop(batch);
        options = new Options(batch);
        title = new TitleScreen(batch, name);
        fightScreen = new FightScreen(batch);
        setScreen(title);
    }

    public MyScreen getMyScreen() {
        return game;
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

    public void fightScreen() {
        setScreen(fightScreen);
    }

    public void optionsScreen() {
        setScreen(options);
    }

    public FightScreen getFightScreen() {
        return fightScreen;
    }

    public Shop getShop() {
        return shop;
    }

    public Npc getNpc() {
        return npc;
    }

}
