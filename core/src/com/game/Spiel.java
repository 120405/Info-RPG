package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Spiel extends Game {
    public static Spiel INSTANCE;
    public Fight fight;
    private int money;
    private TitleScreen title;
    private MyScreen game;
    private Shop shop;
    public Inventory inventory;
    private FightScreen fightScreen;
    private Options options;
    private final String name;
    private Npc npc;
   private Database db;
    public boolean saveEnabled;
    private GUI_Item[][] items;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch,batch2;
    private BitmapFont font;
    private GUI inventoryGUI;
    private AISteering aiSteering;

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

    public GUI_Item[][] getItems() {
       return items;
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
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        shapeRenderer = new ShapeRenderer();
        inventory = new Inventory();
        fight = new Fight(80, 80, 20, "Monster",1, 100, 100, 10, "Hero");
        batch = new SpriteBatch();
        batch2 = new SpriteBatch();
        db = new Database();
        saveEnabled = db.isSaveFileEnabled();
        items = db.loadItems(new GUI_Item[4][8], saveEnabled);
        inventoryGUI = new GUI();
        game = new MyScreen(batch);
        npc = new Npc(30f);
        aiSteering = new AISteering();
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

    public GUI getInventory() {
        return inventoryGUI;
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
    public Database getDB() {
        return db;
    }
    public Npc getNpc() {
        return npc;
    }

    public  Fight getFight(){
        return fight;
    }
    public void createHealthBars(boolean monster) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(20, 20, fight.getHero().getLP(), 20);
        if (monster) {
            shapeRenderer.rect(Gdx.graphics.getWidth() - (20 + fight.getMonster().getFullLP()), 20,
                    fight.getMonster().getLP(), 20);
        }
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(19, 19, fight.getHero().getFullLP() + 1, 21);
        if (monster) {
            shapeRenderer.rect(Gdx.graphics.getWidth() - (19 + fight.getMonster().getFullLP()), 19,
                    fight.getMonster().getFullLP() + 1, 21);
        }
        shapeRenderer.end();
        batch2.begin();
        font.draw(batch2, fight.getHero().getName(), (int) (fight.getHero().getFullLP() / 2), 61);
        if (monster) {
            font.draw(batch2, fight.getMonster().getName(),
                    Gdx.graphics.getWidth() - (45 + (int) (fight.getMonster().getFullLP() / 2)), 61);
        }
        batch2.end();
    }
    public void dispose() {
        shapeRenderer.dispose();
        font.dispose();
    }
    public BitmapFont getFont() {
        return font;
    }
}
