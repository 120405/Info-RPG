package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Buttons {

    private TextButton button;
    private TextButton.TextButtonStyle style;
    private BitmapFont font;
    private boolean isOpen = false;
    private final Sound accepted = Gdx.audio.newSound(Gdx.files.internal("alarm.mp3"));
    private Color color1;

    public Buttons(String displayedText, Stage stage, final String action, double x, double y, Color color) {
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
                            if (Spiel.INSTANCE.getMoney() >= wx) {
                                Spiel.INSTANCE.buyItem(x, "weapon");
                                Spiel.INSTANCE.moneyDown(wx);
                                accepted.play();
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
                                accepted.play(0.5f);
                        }
                        }
                        break;
                    case "buyDagger":
                        Item z = Spiel.INSTANCE.getInventory().getDagger();
                        int wz = z.getWorth();
                        if (z != Spiel.INSTANCE.fight.getHero().getWeapon()) {
                            if (Spiel.INSTANCE.getMoney() >= wz){
                                Spiel.INSTANCE.buyItem(z, "weapon");
                                Spiel.INSTANCE.moneyDown(wz);
                                accepted.play(0.5f);
                            }
                        }
                        break;
                    case "options":
                        Spiel.INSTANCE.optionsScreen();
                        break;
                    case "fight":
                        Spiel.INSTANCE.fightScreen();
                        break;
                    case "showInv":
                        if(!isOpen) {
                            Spiel.INSTANCE.getFightScreen().inventory.show();
                            isOpen = true;
                        } else {
                            Spiel.INSTANCE.getFightScreen().inventory.hide();
                            isOpen = false;
                        }

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
    public  void setColor1(Color color){
        color1 = color;
    }
}
