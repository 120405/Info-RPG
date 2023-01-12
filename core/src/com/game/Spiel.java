package com.game;

import com.badlogic.gdx.Game;

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
