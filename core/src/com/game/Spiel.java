package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.ai.Npc;
import com.game.database.Database;
import com.game.fight.Fight;
import com.game.items.GUI;
import com.game.items.GUI_Item;
import com.game.items.Inventory;
import com.game.items.Item;
import com.game.screens.FightScreen;
import com.game.screens.MyScreen;
import com.game.screens.Options;
import com.game.screens.SelectScreen;
import com.game.screens.TitleScreen;

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
	private Database db;
	private GUI inventoryGUI;
	private GUI_Item[][] items;
	public boolean saveEnabled;
	private int heroLP = 100;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font;
	private SpriteBatch batch2;
	private SpriteBatch batch;
	private SelectScreen select;
	public boolean ngame,lgame,lgame2 = false;

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
		db = new Database();
		saveEnabled = db.isSaveFileEnabled();
		batch2 = new SpriteBatch();
		batch = new SpriteBatch();
		options = new Options(batch);
		title = new TitleScreen(batch, name);
		select = new SelectScreen(batch);
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		shapeRenderer = new ShapeRenderer();
		fight = new Fight(80, 80, 20, "Monster", heroLP, 100, 20, "Hero");
		inventory = new Inventory();
		inventoryGUI = new GUI();
		shop = new Shop(batch);
		fightScreen = new FightScreen(batch);
		game = new MyScreen(batch);
		npc = new Npc(1f);
		// player= new Player();
		setScreen(title);
	}

	public MyScreen getMyScreen() {
		return game;
	}

	public void selectScreen() {
		setScreen(select);
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

	public GUI getInventoryGUI() {
		return inventoryGUI;
	}

	public Database getDatabase() {
		return db;
	}

	public GUI_Item[][] getItems() {
		return items;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setHeroLP(int lp) {
		heroLP = lp;
		fight.getHero().setLP(lp);
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

	public void loadItems() {
		items = db.loadItems(new GUI_Item[4][8], saveEnabled);
		inventoryGUI.addInventory(inventoryGUI.getTable(), items);
	}

}
