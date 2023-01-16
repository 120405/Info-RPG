package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import sun.security.provider.ConfigFile;

public class Buttons {
    TextButton button;
    TextButton.TextButtonStyle s;
    BitmapFont font;

    public Buttons(String displayedText, Stage stage, final String action, double x, double y, Color color) {
        font = new BitmapFont();
        s = new TextButton.TextButtonStyle();
        s.font = font;
        s.fontColor = color;
        button = new TextButton(displayedText, s);
        button.getLabel().setFontScale(5F);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        button.setPosition((int)(Gdx.graphics.getWidth()/x), (int)(Gdx.graphics.getHeight()/y));
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                switch (action) {
                    case "game":
                        Spiel.INSTANCE.gameScreen();
                        break;
                    case "title":
                        Spiel.INSTANCE.titleScreen();
                        break;
                    case "shop":
                        Spiel.INSTANCE.shopScreen();
                        break;
                    case "quit":
                        System.exit(0);
                        break;
                    case "buySword":
                        Item x = Spiel.INSTANCE.getInventory().getSword();
                        int wx = x.getWorth();
                        if (x != Spiel.INSTANCE.fight.getHero().getWeapon()) {
                            if (Spiel.INSTANCE.getMoney() >= 50) {
                                Spiel.INSTANCE.buyItem(x, "weapon");
                                Spiel.INSTANCE.moneyDown(50);
                            }
                        }
                        break;
                    case "buyShield":
                        Item y = Spiel.INSTANCE.getInventory().getShield();
                        int wy = y.getWorth();
                        if (y != Spiel.INSTANCE.fight.getHero().getShield()) {
                            if (Spiel.INSTANCE.getMoney() >= wy){
                                Spiel.INSTANCE.buyItem(y, "shield");
                            Spiel.INSTANCE.moneyDown(wy);
                        }
                        }
                        break;
                    case "options":
                        Spiel.INSTANCE.optionsScreen();
                        break;
                    case "fight":
                        Spiel.INSTANCE.fightScreen();
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
