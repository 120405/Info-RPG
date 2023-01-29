package com.game.items;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item {

private Image look;
private String name;
private int dur;
private int atk;
private int def;
private int worth;
private String effect;


public Item(Image look, String name, int dur, int atk, int def, int worth, String effect) {
	this.look = look;
	this.name = name;
	this.dur = dur;
	this.atk = atk;
	this.def = def;
	this.worth = worth;
	this.effect = effect;
}

public Image getLook(){
	return look;
}

public String getName(){
	return name;
}

public String getEffect(){
	return effect;
}

public int getDur(){
	return dur;
}

public int getAtk(){
	return atk;
}

public int getDef(){
	return def;
}

public void setLook(Image x) {
	look=x;
}

public void setName(String x) {
	name=x;
}

public void setEffect(String x) {
	effect=x;
}

public void setDur(int x) {
	dur=x;
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
}
