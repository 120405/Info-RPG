package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

import java.awt.*;
import java.util.HashMap;

public class GUI {
    private final Window window,equipWindow,statsWindow;
    private Texture img;
    private Table table, equipTable;
    private boolean isOpen;
    private Buttons equip, unequip, use;
    private int width, height;
    private final Sound accepted = Gdx.audio.newSound(Gdx.files.internal("alarm.mp3"));
    private GUI_Item currentItem;
    private Label.LabelStyle style;
    private HashMap<Integer,Label> label;

    public GUI() {
        currentItem = null;
        width = 30;
        height = 30;
        isOpen = false;
        img = new Texture("Inventory.png");
        Window.WindowStyle windowStyle = new Window.WindowStyle(new BitmapFont(), Color.WHITE, new SpriteDrawable(new Sprite(img)));
        Window.WindowStyle windowStyleEquip = new Window.WindowStyle(new BitmapFont(), Color.WHITE, new SpriteDrawable(new Sprite(new Texture("transparent_background.png"))));
        style = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        window = new Window("", windowStyle);
        equipWindow = new Window("", windowStyleEquip);
        statsWindow = new Window("", windowStyleEquip);
        buildStatsWindow(statsWindow);
        window.setSize(240, 120);
        equipWindow.setSize(80, 80);
        equipWindow.setScale(3f);
        window.setPosition(400, 200);
        equipWindow.setPosition(1200, 200);
        equipTable = new Table();
        buildEquipMenu(equipWindow, equipTable);
        statsWindow.setSize(300, 200);
        window.setScale(3f);
        statsWindow.setScale(1.5f);
        table = new Table();
        addInventory(table, Spiel.INSTANCE.getItems());
        window.setVisible(false);
        equipWindow.setVisible(false);
        statsWindow.setVisible(false);

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
        t.setFillParent(true);
        equip =  new Buttons("Ausrüsten", t, "equip", 0, 0, Color.BLACK, 80, 20);
        t.row();
        unequip = new Buttons("Ablegen", t, "unequip", 0, 0, Color.BLACK, 80, 20);
        t.row();
        use = new Buttons("Benutzen", t, "use", 0, 0, Color.BLACK, 80, 20);
        t.row();
        new Buttons("Schließen", t, "closeEquipWindow", 0, 0, Color.BLACK, 80, 20);
        w.add(t);

    }
    public void setPosition(int x, int y) {
        window.setPosition(x, y);
    }
    public Window getStatsWindow() {
        return statsWindow;
    }

    public void addInventory(Table table, GUI_Item[][] items) {
        table.setFillParent(true);
        for (int a = 0; a < 4; a++) {
            for (int i = 0; i < 8; i++) {

                //  table.add(items[a][i].getItemStack().getChild(0)).size(30, 30); just background
                table.add(items[a][i].getItemStack()).size(width, height);
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
    public void buildStatsWindow(Window window) {
        label = new HashMap<>();
        Table table = new Table();
        label.put(0,new Label("Name: ", style));
        label.put(1,new Label("Haltbarkeit:", style));
        label.put(2,new Label("Schaden: ", style));
        label.put(3,new Label("Verteidigung: ", style));
        label.put(4,new Label("Wert: ", style));
        label.put(5,new Label("Gewicht: ", style));
        label.put(6,new Label("Effekt: ", style));
        label.put(7,new Label("Fähigkeit: ", style));

        for(int i = 0; i < label.size(); i++) {
            table.add(label.get(i)).left().padRight(150);
            table.row();
        }
        window.add(table);
    }
    public void checkItems(float x, float y, Image img, float backgroundW, float backgroundH, Stack current,
                           String name, int durability, int atk, int def, int worth,int weight, Effect effect, String skill, boolean owner) {

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
                            it.setAtk(atk);
                            it.setDef(def);
                            it.setEffect(effect);
                            it.setSkill(skill);
                            it.setWeight(weight);
                            it.setWorth(worth);
                            it.setOwner(owner);
                        }
                    }
                }
            }
        }
    }
    public boolean checkItemsShop(Stack stack, Image img, int x, int y, String type, String name, int durability, int atk, int def, int worth,int weight, Effect effect, String skill, boolean owner) {
        @SuppressWarnings("rawtypes")
        Array<Cell> c = table.getCells();
        for(int i = 0; i < c.size; i++) {
            if(((int)(table.localToScreenCoordinates(new Vector2(c.get(i).getActor().getX(),c.get(i).getActor().getY())).x) <= x) &&
              (((int)(table.localToScreenCoordinates(new Vector2(c.get(i).getActor().getX(),c.get(i).getActor().getY())).x) + width*(height/10)) >= x)) {
            if((Gdx.graphics.getHeight() - (int)(table.localToScreenCoordinates(new Vector2(c.get(i).getActor().getX(),c.get(i).getActor().getY())).y) <= y) &&
               (Gdx.graphics.getHeight() - (int)(table.localToScreenCoordinates(new Vector2(c.get(i).getActor().getX(),c.get(i).getActor().getY())).y) + 90 >= y)) {
                 Stack s = (Stack) (c.get(i).getActor());
                SnapshotArray<Actor> a = s.getChildren();
                 if(a.size == 1) {
                     s.add(img);
                     GUI_Item it = findItem(img);

                    if ((worth <= Spiel.INSTANCE.getMoney())) {
                        if(Spiel.INSTANCE.getHero().hasItem(type)) {
                            if(Spiel.INSTANCE.getHero().getItem(type) != name) {
                                it.setName(name);
                                it.setType(type);
                                it.setDurability(durability);
                                it.setAtk(atk);
                                it.setDef(def);
                                it.setEffect(effect);
                                it.setSkill(skill);
                                it.setWeight(weight);
                                it.setWorth(worth);
                                it.setOwner(true);
                            }
                        } else {
                            if (name != "") {
                                it.setName(name);
                                it.setType(type);
                                it.setDurability(durability);
                                it.setAtk(atk);
                                it.setDef(def);
                                it.setEffect(effect);
                                it.setSkill(skill);
                                it.setWeight(weight);
                                it.setWorth(worth);
                                it.setOwner(true);
                             
                            }
                        }

                        if(!owner) {
                        Spiel.INSTANCE.buyItem(it, it.getType());
                        Spiel.INSTANCE.moneyDown(it.getWorth());
                        accepted.play(0.5f);
                        owner = true;
                        Spiel.INSTANCE.getItems()[findItemPosition(s).x][findItemPosition(s).y] = it;
                        }
                     } else {
                        s.removeActor(img);
                         stack.add(img);
                     }
                 }
           }
            }
        }
    return owner;

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
    public GUI_Item findItem(Image img) {
        GUI_Item item = null;
        for (int a = 0; a < 4; a++) {
            for (int i = 0; i < 8; i++) {
                SnapshotArray<Actor> e = Spiel.INSTANCE.getItems()[a][i].getItemStack().getChildren();
                if (e.size > 1 && e.items[1] == img) {
                    item = Spiel.INSTANCE.getItems()[a][i];
                }
            }
        }
        return item;

    }
    public Point findItemPosition(Stack stack) {
        Point p = new Point();
        for (int a = 0; a < 4; a++) {
            for (int i = 0; i < 8; i++) {
                if (Spiel.INSTANCE.getItems()[a][i].getItemStack() == stack) {
                    p.x = a;
                    p.y = i;
                }
            }
        }
        return p;
    }
    public GUI_Item findItemHashMap(String name) {
    	HashMap<Integer, GUI_Item> map = Spiel.INSTANCE.inventory.getItemHashMap();
    	for(int i = 0; i < map.size(); i++) {
    		if(map.get(i).getName() == name) {
    			return map.get(i);
    		}		
    	}
    	return null;
    }

    public Table getTable() {
        return table;
    }
    public void openEquipWindow(Image img,boolean consumable, int x, int y, String name, boolean owner) {
    	 if(findItem(img)  != null) {
             currentItem  = findItem(img);
    	 } else if(findItemHashMap(name) != null){
             currentItem  = findItemHashMap(name);
    	 }
         if(owner) {
             if (consumable) {
                 equip.hide();
                 unequip.hide();
                 use.show();
             } else {

                 if (Spiel.INSTANCE.getHero().hasItem(currentItem.getType()) && name == Spiel.INSTANCE.getHero().getItem(currentItem.getType())) {
                     unequip.show();
                     equip.hide();
                 } else {
                     unequip.hide();
                     equip.show();
                 }
                 use.hide();
             }
             equipWindow.setPosition(x+10, Gdx.graphics.getHeight() - y+10);
             equipWindow.setVisible(true);
         } else {
             openStatsWindow(x+20, Gdx.graphics.getHeight() - y-20, currentItem);



         }
    }
    public void openStatsWindow(int x, int y, GUI_Item item) {
        if(item != null) {
            label.get(0).setText("Name: " + item.getName());
            label.get(1).setText("Haltbarkeit: " + item.getDur());
            label.get(2).setText("Schaden: " + item.getAtk());
            label.get(3).setText("Verteidigung: " + item.getDef());
            label.get(4).setText("Wert: " + item.getWorth());
            label.get(5).setText("Gewicht: " + item.getWeight());
            label.get(6).setText("Effekt: " + item.getEffect().getName());
            label.get(7).setText("Fähigkeit: " + item.getSkill());
        }
        statsWindow.setPosition(x, y);
        statsWindow.setVisible(true);
    }
    public void closeEquipWindow() {
        equipWindow.setVisible(false);
        statsWindow.setVisible(false);
        for(int i = 0; i < label.size(); i++) {
            label.get(i).setText("");
        }
    }
    public GUI_Item getCurrentItem() {
        return currentItem;
    }

}
