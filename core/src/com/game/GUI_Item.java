package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUI_Item {
    private Stack stack;
    private Image background;
    private Image itemImage;
    public GUI_Item() {
        background = new Image(new Texture("Inventory_background.png"));
        stack = new Stack(background);
    }
    public GUI_Item(Image itemImage) {
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
      //  return stack.getChild(1) != null;
        //fehler: getChild(1) ist null; somit NullPointerException
        return false;
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
                img.setVisible(false);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

              Spiel.INSTANCE.getFightScreen().inventory.checkItems(x, y, img, background.getWidth(), background.getHeight(), stack);
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
}
