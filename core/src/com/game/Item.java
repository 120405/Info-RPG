package com.game;

public class Item {

String look;
String name;
int dur;
int atk;
int def;
String effect;


public Item() {
	look = "";
	name = "";
	dur = 0;
	atk = 0;
	def = 0;
	effect = "";
}

public void create() {
	
}

public String getLook(){
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

public void setLook(String x) {
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
}
