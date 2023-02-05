package com.game;

public class Item {

private String look;
private String name;
private int dur;
private int atk;
private int def;
private int worth;
private int weight;
private Effect effect;
private  String skill;


public Item(String look, String name, int dur, int atk, int def, int worth,int weight, Effect effect, String skill) {
	this.look = look;
	this.name = name;
	this.dur = dur;
	this.atk = atk;
	this.def = def;
	this.worth = worth;
	this.weight = weight;
	this.effect = effect;
	this.skill = skill;
}

public String getLook(){
	return look;
}

public String getName(){
	return name;
}

public Effect getEffect(){
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

public void setLook(String x) {
	look=x;
}

public void setName(String x) {
	name=x;
}

public void setEffect(Effect x) {
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
