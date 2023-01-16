package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Buttons {
    TextButton button;
    TextButton.TextButtonStyle s;
    BitmapFont font;

    public Buttons(String displayedText, Stage stage, final String screen, double x, double y) {
        font = new BitmapFont();
        s = new TextButton.TextButtonStyle();
        s.font = font;
        s.fontColor = Color.BLACK;
        button = new TextButton(displayedText, s);
        button.getLabel().setFontScale(5F);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        button.setPosition((int)(Gdx.graphics.getWidth()/x), (int)(Gdx.graphics.getHeight()/y));
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                switch (screen) {
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
