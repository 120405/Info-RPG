package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Buttons {

    private TextButton button;
    private TextButton.TextButtonStyle style;
    private BitmapFont font;
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
        addListener(action);
    }
    public Buttons(String displayedText, Table t, final String action, double x, double y, Color color, int width, int height) {
        font = new BitmapFont();
        style = new TextButton.TextButtonStyle();
        style.font = font;
        color1 = color;
        style.fontColor = color1;
        button = new TextButton(displayedText, style);
        button.getLabel().setFontScale(1F);
        button.setPosition((int)(x), (int)(y));
        t.add(button).size(width, height);
        addListener(action);
    }
    public void addListener(final String action) {
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
                        if(Spiel.INSTANCE.saveEnabled) {
                            Spiel.INSTANCE.getDB().saveOptions();
                            Spiel.INSTANCE.getDB().save();
                        }
                        System.exit(0);
                        break;
                    case "buySword":
                        GUI_Item x = Spiel.INSTANCE.inventory.getSword();
                        int wx = x.getWorth();
                        if (x != Spiel.INSTANCE.fight.getHero().getWeapon()) {
                            if (Spiel.INSTANCE.getMoney() >= wx) {
                                Spiel.INSTANCE.buyItem(x, "weapon");
                                Spiel.INSTANCE.moneyDown(wx);
                                accepted.play(0.5f);
                            }
                        }
                        break;
                    case "buyShield":
                        GUI_Item y = Spiel.INSTANCE.inventory.getShield();
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
                        GUI_Item z = Spiel.INSTANCE.inventory.getDagger();
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
                        if(!Spiel.INSTANCE.getInventory().isOpen()) {
                            Spiel.INSTANCE.getInventory().show();
                        } else {
                            Spiel.INSTANCE.getInventory().hide();
                            if(Spiel.INSTANCE.getInventory().getEquipWindow().isVisible()) {
                                Spiel.INSTANCE.getInventory().getEquipWindow().setVisible(false);
                            }
                        }

                        break;
                    case "save":
                        if (Spiel.INSTANCE.saveEnabled == false) {
                            style.fontColor = Color.GREEN;
                            Spiel.INSTANCE.saveEnabled = true;
                        } else {
                            style.fontColor = Color.RED;
                            Spiel.INSTANCE.saveEnabled = false;
                        }
                        break;
                    case "closeEquipWindow":
                        Spiel.INSTANCE.getInventory().closeEquipWindow();
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
    public void hide() {
        button.setVisible(false);
    }
    public void show() {
        button.setVisible(true);
    }
    public void delete() {
        button.remove();
    }
}
