package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SnapshotArray;

public class GUI_Item {
    private Stack stack;
    private Image background;
    private Image itemImage;
    private String name;
    private int atk;
    private int durability;
    private boolean consumable = false;
    private int def;
    private int worth;
    private int weight;
    private Effect effect;
    private  String skill;
    public GUI_Item() {
        name = "";
        durability = 0;
        background = new Image(new Texture("Inventory_background.png"));
        stack = new Stack(background);
    }
    public GUI_Item(Image itemImage, boolean consumable) {
        this.consumable = consumable;
        background = new Image(new Texture("Inventory_background.png"));
        stack = new Stack(background,itemImage);
        buildListener(itemImage);
    }
    public GUI_Item(String name, int dur, int atk, int def, int worth,int weight, Effect effect, String skill, Image itemImage, boolean consumable) {
        this.name = name;
        durability = dur;
        this.atk = atk;
        this.def = def;
        this.worth = worth;
        this.weight = weight;
        this.effect = effect;
        this.skill = skill;
        this.consumable = consumable;
        background = new Image(new Texture("Inventory_background.png"));
        stack = new Stack(background,itemImage);
        buildListener(itemImage);
    }
    public Stack getItemStack() {
        return stack;
    }
    public Image getItem() {
        return (Image) stack.getChild(1);
    }
    public boolean doesItemExist() {
        boolean exist = false;
        SnapshotArray<Actor> array = stack.getChildren();
        if(((SnapshotArray<?>) array).size == 2) {
            exist = true;
        }
        return exist;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDurability(int durability) {
        this.name = name;
    }
    public void buildListener(final Image img) {
        final float currentPosX = img.getX();
        final float currentPosY = img.getY();
        img.setTouchable(Touchable.enabled);
        img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                 stack.removeActor(img);
                 stack.add(img);
                 img.setVisible(false);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

              Spiel.INSTANCE.getInventory().checkItems(x, y, img, background.getWidth(), background.getHeight(), stack, name, durability);
                img.setVisible(true);
            }
            @Override
            public void enter (InputEvent event, float x, float y, int pointer, @Null Actor fromActor) {
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    Spiel.INSTANCE.getInventory().openEquipWindow(consumable, Gdx.input.getX(),Gdx.input.getY());
                }

            }
            @Override
            public void exit (InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    Spiel.INSTANCE.getInventory().closeEquipWindow();
                }
            }
        });



    }
    public float getStackX() {
        return stack.getChild(0).getX();
    }
    public float getStackY() {
        return stack.getChild(0).getX();
    }
    public Image getBackgroundImage() {
        return background;
    }
    public String getName(){
        return name;
    }

    public Effect getEffect(){
        return effect;
    }

    public int getDur(){
        return durability;
    }

    public int getAtk(){
        return atk;
    }

    public int getDef(){
        return def;
    }



    public void setEffect(Effect x) {
        effect=x;
    }

    public void setDur(int x) {
        durability=x;
    }

    public void setAtk(int x) {
        atk=x;
    }

    public void setDef(int x) {
        def=x;
    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
