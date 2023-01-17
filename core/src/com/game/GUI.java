package com.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GUI {
    private Window window;
    public GUI()

    {
        Window.WindowStyle windowStyle = new Window.WindowStyle(new BitmapFont(), Color.WHITE, new SpriteDrawable(new Sprite(new Texture("Background.png"))));
        window = new Window("", windowStyle);
        window.setScale(4f);
        window.setPosition(200, 200);
        //window.pack();
        window.setVisible(false);

    }
    public Window getInventory() {
        return window;
    }
   /* public void addComponent() {
        window.add(); für spätere weitere Komponenten
    }*/
    public void addComponent(Buttons b) {
        window.add(b.getButton()).pad(10, 0, 10, 0).row();
    }
    public void renderWindow(float delta) {
        window.act(delta);
    }
}
