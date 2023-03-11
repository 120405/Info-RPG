package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class GUI {
    private final Window window;
    private final Window equipWindow;
    private Texture img;
    private Table table, equipTable;
    private Image renderImage;
    private boolean isOpen;

    public GUI() {
        isOpen = false;
        renderImage = null;
        img = new Texture("Inventory.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(new BitmapFont(), Color.WHITE, new SpriteDrawable(new Sprite(img)));
        window = new Window("", windowStyle);
        equipWindow = new Window("", windowStyle);
        window.setSize(240, 120);
        equipWindow.setSize(80, 120);
        equipWindow.setScale(3f);
        window.setPosition(400, 200);
        equipWindow.setPosition(1200, 200);
        equipTable = new Table();
        buildEquipMenu(equipWindow, equipTable);
        window.setScale(3f);
         table = new Table();

        addInventory(table, Spiel.INSTANCE.getItems());
        window.setVisible(false);
        equipWindow.setVisible(false);


    }
    public Window getInventory() {
        return window;
    }

    /* public void addComponent() {
         window.add(); for later added components
     }*/
    public void show() {
        window.setVisible(true);
        isOpen = true;
    }

    public void hide() {
        //disable if not visible: no invisible inventory during fight
        window.setVisible(false);
        equipWindow.setVisible(false);
        isOpen = false;
    }
    public void buildEquipMenu(Window w, Table t) {
        new Buttons("Close", t, "closeEquipWindow", 16, 16, Color.OLIVE);
         w.add(t);
    }
    public void setPosition(int x, int y) {
        window.setPosition(x, y);
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
    public Window getEquipWindow() {
        return equipWindow;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public void checkItems(float x, float y, Image img, float backgroundW, float backgroundH, Stack current,
                           String name, int durability) {
        renderImage = img;
        Stack destination = current;
        @SuppressWarnings("rawtypes")
        Array<Cell> c = table.getCells();
        for (int i = 0; i < 4 * 8; i++) {
            if (c.get(i).getActorX() <= (current.getX() + x)
                    && (current.getX() + x) <= (c.get(i).getActorX() + backgroundW)) {
                if (c.get(i).getActorY() <= (current.getY() + y)
                    && (current.getY() + y) <= (c.get(i).getActorY() + backgroundH)) {
                    destination = (Stack) (c.get(i).getActor());
                    SnapshotArray<Actor> a = destination.getChildren();
                    if (a.size == 1) {
                        if (destination != current) {
                            current.removeActor(img);
                        }
                        destination.add(img);
                        if (name != "") {
                            GUI_Item it = findItem(destination);
                            it.setName(name);
                            it.setDurability(durability);
                        }
                    }
                }
            }
        }
    }
    public GUI_Item findItem(Stack s) {
        GUI_Item item = null;
        for (int a = 0; a < 4; a++) {
            for (int i = 0; i < 8; i++) {
                if (Spiel.INSTANCE.getItems()[a][i].getItemStack() == s) {
                    item = Spiel.INSTANCE.getItems()[a][i];
                }
            }
        }
        return item;

    }



    public Table getTable() {
        return table;
    }
    public void openEquipWindow() {
        equipWindow.setVisible(true);
    }
    public void closeEquipWindow() {
        equipWindow.setVisible(false);
    }
}
