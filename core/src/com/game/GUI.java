package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GUI {
    private Window window;
    private Texture img;
    public GUI()

    {
        img =  new Texture("Inventory.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(new BitmapFont(), Color.WHITE, new SpriteDrawable(new Sprite(img)));
        window = new Window("", windowStyle);
        window.setSize(img.getWidth(), img.getHeight());
        window.setPosition(200, 200);
        window.setScale(3f);
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
    public void show() {
    window.setVisible(true);
    }
    public void hide() {
        //disable if not visible: no invisible inventory during fight
        window.setVisible(false);
    }
}
