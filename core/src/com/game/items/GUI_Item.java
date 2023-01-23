package com.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.game.Spiel;

public class GUI_Item {
    private Stack stack;
    private Image background;
    private Image itemImage;
    private String name = "";
    private int durability = 0;
    
    public GUI_Item() {
        background = new Image(new Texture("Inventory_background.png"));
        stack = new Stack(background);
    }
    public GUI_Item(String name, int durability) {
    	this.name =  name;
    	this.durability = durability;
    	itemImage = new Image(new Texture("crystal.png"));
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
      if(array.size == 2) {
    	  exist = true;
      }
        return exist;
    }
    public void buildListener(final Image img) {
        img.setTouchable(Touchable.enabled);
        img.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            	Spiel.INSTANCE.getFightScreen().inventory.checkItems(x, y, img, background.getWidth(), background.getHeight(), stack, "", 0);
               img.setVisible(false);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
             Spiel.INSTANCE.getFightScreen().inventory.checkItems(x, y, img, background.getWidth(), background.getHeight(), stack,name, durability);
                img.setVisible(true);
                
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
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public void setDurability(int durability) {
    	this.durability = durability;
    }
    public void render(float delta) {
    	
    }
}
