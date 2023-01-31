package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

public class GUI {
    private final Window window;
    private Texture img;
    private GUI_Item[][] items;
    private Table table;

    public GUI() {
        items = new GUI_Item[4][8];
        img = new Texture("Inventory.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(new BitmapFont(), Color.WHITE, new SpriteDrawable(new Sprite(img)));
        window = new Window("", windowStyle);
        window.setSize(240, 120);
        window.setPosition(400, 400);
        window.setScale(3f);
         table = new Table();
        addInventory(table, buildItems());
        window.setVisible(false);

    }

    public Window getInventory() {
        return window;
    }

    /* public void addComponent() {
         window.add(); for later added components
     }*/
    public void show() {
        window.setVisible(true);
    }

    public void hide() {
        //disable if not visible: no invisible inventory during fight
        window.setVisible(false);
    }

    public void setPosition(int x, int y) {
        window.setPosition(x, y);
    }

    public GUI_Item[][] buildItems() {

        for (int a = 0; a < 4; a++) {
            for (int i = 0; i < 7; i++) {
                items[a][i] = new GUI_Item();

            }
            Image img = new Image(new Texture("crystal.png"));
            items[a][7] = new GUI_Item(img);
        }
        return items;
    }

    public void addInventory(Table table, GUI_Item[][] items) {
        table.setFillParent(true);
        for (int a = 0; a < 4; a++) {
            for (int i = 0; i < 8; i++) {

                //  table.add(items[a][i].getItemStack().getChild(0)).size(30, 30); just background
                table.add(items[a][i].getItemStack()).size(30, 30);
            }
            table.row();
        }
        window.add(table);


    }

    public void checkItems(float x, float y, Image img, float backgroundW, float backgroundH, Stack current) {
        Array<Cell> c = table.getCells();
            for (int i = 0; i < 4*8; i++) {
               if (c.get(i).getActorX() <= Math.abs(x) && Math.abs(x) <= (c.get(i).getActorX() + backgroundW) ) {
                    if(c.get(i).getActorY() <= Math.abs(y) && Math.abs(y) <= (c.get(i).getActorY() + backgroundH)) {
                        System.exit(0);
                    // unterhalb der items funktionier die abfrage auch und das spiel schließt sich?????

                    }
                    }




        }

    }
}
