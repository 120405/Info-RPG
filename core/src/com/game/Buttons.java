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
                    case "quit":
                        if (Spiel.INSTANCE.saveEnabled) {
                            Spiel.INSTANCE.getDB().saveOptions();
                            Spiel.INSTANCE.getDB().save();
                        }
                        System.exit(0);
                        break;
                    case "options":
                        Spiel.INSTANCE.optionsScreen();
                        break;
                    case "fight":
                        Spiel.INSTANCE.fightScreen();
                        break;
                    case "showInv":
                        if (!Spiel.INSTANCE.getInventory().isOpen()) {
                            Spiel.INSTANCE.getInventory().show();
                        } else {
                            Spiel.INSTANCE.getInventory().hide();
                            if (Spiel.INSTANCE.getInventory().getEquipWindow().isVisible()) {
                                Spiel.INSTANCE.getInventory().getEquipWindow().setVisible(false);
                            }
                        }

                        break;
                    case "save":
                        if (!Spiel.INSTANCE.saveEnabled) {
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
                    case "equip":
                        switch (Spiel.INSTANCE.getInventory().getCurrentItem().getType()) {
                            case "weapon":
                                Spiel.INSTANCE.getHero().setWeapon(Spiel.INSTANCE.getInventory().getCurrentItem());
                                Spiel.INSTANCE.getInventory().closeEquipWindow();
                                break;
                            case "shield":
                                Spiel.INSTANCE.getHero().setShield(Spiel.INSTANCE.getInventory().getCurrentItem());
                                Spiel.INSTANCE.getInventory().closeEquipWindow();
                                break;
                            case "armor":
                                Spiel.INSTANCE.getHero().setArmor(Spiel.INSTANCE.getInventory().getCurrentItem());
                                Spiel.INSTANCE.getInventory().closeEquipWindow();
                                break;
                            default:
                                break;
                        }
                        break;
                    case "unequip":

                            switch (Spiel.INSTANCE.getInventory().getCurrentItem().getType()) {
                                case "weapon":
                                    Spiel.INSTANCE.getHero().setWeapon(null);
                                    Spiel.INSTANCE.getInventory().closeEquipWindow();
                                    break;
                                case "shield":
                                    Spiel.INSTANCE.getHero().setShield(null);
                                    Spiel.INSTANCE.getInventory().closeEquipWindow();
                                    break;
                                case "armor":
                                    Spiel.INSTANCE.getHero().setArmor(null);
                                    Spiel.INSTANCE.getInventory().closeEquipWindow();
                                    break;
                                default:
                                    break;
                            }

                        break;
                    case "use":

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
