package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.HashMap;

public class ShopWindow {
    private Window window;
    private Table table;
    public ShopWindow() {
        Window.WindowStyle windowStyle = new Window.WindowStyle(new BitmapFont(), Color.WHITE, new SpriteDrawable(new Sprite(new Texture("transparent_background.png"))));
        window = new Window("", windowStyle);
        table = new Table();
        HashMap<Integer, GUI_Item> map = Spiel.INSTANCE.inventory.getItemHashMap();
        for(int i = 0; i < map.size(); i++) {
        table.add(map.get(i).getItemStack()).size(30, 30);
        }

        window.add(table);
        window.setPosition(400, 700);
        window.setVisible(true);
    }
    public Window getWindow() {
        return window;
    }

}
