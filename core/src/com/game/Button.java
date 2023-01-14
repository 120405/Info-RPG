package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Button {
    TextButton button;
    TextButton.TextButtonStyle s;
    BitmapFont font;

    public Button(String displayedText, Stage stage, final String screen, int x, int y) {
        font = new BitmapFont();
        s = new TextButton.TextButtonStyle();
        s.font = font;
        s.fontColor = Color.BLACK;
        button = new TextButton(displayedText, s);
        button.getLabel().setFontScale(5F);
        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
        button.setPosition(x, y);
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
                }

            }
        });
    }
}
