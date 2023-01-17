package com.game;

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
        window.setScale(3f);
        window.setPosition(200, 200);
        SpriteDrawable upDrawble = new SpriteDrawable(new Sprite(new Texture("Background.png")));
        SpriteDrawable downDrawble = new SpriteDrawable(new Sprite(new Texture("Background.png")));
        SpriteDrawable cheDrawble = new SpriteDrawable(new Sprite(new Texture("Background.png")));
        TextButton.TextButtonStyle btStyle = new TextButton.TextButtonStyle(upDrawble, downDrawble, cheDrawble, new BitmapFont());
        TextButton playButton = new TextButton("play", btStyle);
        window.add(playButton).pad(10, 0, 10, 0).row();
        //window.pack();
        window.setVisible(false);

    }
    public Window getInventory() {
        return window;
    }
}
