package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Spiel extends Game {
	public static Spiel INSTANCE;

	public Spiel() {
		if(INSTANCE == null) {
			INSTANCE = this;
		}
	}
	public void create () {
		setScreen(new TitleScreen());
	}
}
