package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.items.Item;

public class Buttons {

    private TextButton button;
    private TextButton.TextButtonStyle style;
    private BitmapFont font;
    private boolean isOpen = false;
    private final Sound accepted = Gdx.audio.newSound(Gdx.files.internal("alarm.mp3"));
    private Color color1;
    private Spiel game;

    public Buttons(String displayedText, Stage stage, final String action, double x, double y, Color color) {
    	game = Spiel.INSTANCE;
        font = new BitmapFont();
        style = new TextButton.TextButtonStyle();
        style.font = font;
        color1 = color;
        style.fontColor = color1;
        button = new TextButton(displayedText, style);
        button.getLabel().setFontScale(5F);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        button.setPosition((int)(Gdx.graphics.getWidth()/x), (int)(Gdx.graphics.getHeight()/y));
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                switch (action) {
                    case "game":
                    	game.getInventoryGUI().getInventory().clear();
                    	game.getDatabase().setGameID(0);
                    	game.loadItems();
                    	game.gameScreen();
                        break;
                    case "title":
                    	game.titleScreen();
                        break;
                    case "shop":
                    	game.shopScreen();
                        break;
                    case "quit":               	
                    	if(game.saveEnabled) {
                    		if(!game.getDatabase().doesGameExist()) {
                    			game.getDatabase().setGameID(1);;
                       	}
                    		game.getDatabase().save(game.getItems(), game.fight.getHero().getLP(), game.fight.getMonster().getLP(), game.getMoney());
                    	}
                    	game.getDatabase().saveOptions();
                        System.exit(0);
                        break;
                    case "buySword":
                        Item x = game.getInventory().getSword();
                        int wx = x.getWorth();
                        if (x != game.fight.getHero().getWeapon()) {
                            if (game.getMoney() >= wx) {
                            	game.buyItem(x, "weapon");
                            	game.moneyDown(wx);
                                accepted.play(0.5f);
                            }
                        }
                        break;
                    case "buyShield":
                        Item y = game.getInventory().getShield();
                        int wy = y.getWorth();
                        if (y != game.fight.getHero().getShield()) {
                            if (game.getMoney() >= wy){
                            	game.buyItem(y, "shield");
                            	game.moneyDown(wy);
                                accepted.play(0.5f);
                        }
                        }
                        break;
                    case "buyDagger":
                        Item z = game.getInventory().getDagger();
                        int wz = z.getWorth();
                        if (z != game.fight.getHero().getWeapon()) {
                            if (game.getMoney() >= wz){
                            	game.buyItem(z, "weapon");
                            	game.moneyDown(wz);
                                accepted.play(0.5f);
                            }
                        }
                        break;
                    case "options":
                    	game.optionsScreen();
                        break;
                    case "fight":
                    	game.fightScreen();
                        break;
                    case "showInv":
                        if(!isOpen) {
                        	game.getFightScreen().inventory.show();
                            isOpen = true;
                        } else {
                        	game.getFightScreen().inventory.hide();
                            isOpen = false;
                        }

                       break;
                    case "save":
                    	if(game.saveEnabled == false) {
                    		style.fontColor = Color.GREEN;
                    		game.saveEnabled = true;
                    	} else {
                    		style.fontColor = Color.RED;
                    		game.saveEnabled = false;
                    	}
                    	break;
                    case "toGame":
                    	if(!game.fight.getWinner().equals("")) {
                    		game.gameScreen();
                    	}
                    	break;
                    case "load":
                    	game.selectScreen();
                    	
                    	break;
                    case "loadGame":
                    	game.getInventoryGUI().getInventory().clear();
                    	game.getDatabase().setGameID(1);
                    	game.loadItems();//in einen menüscreen überleiten,in welchem die befehle dann ausgeführt werden können
                    	game.gameScreen();
                    	break;
                    default:
                        break;
                }

            }
        });
    }
    public TextButton getButton() {
        return button;
    }
    public void setText(String text) {
    	button.setText(text);
    }

}
