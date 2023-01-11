package com.game;


public class Monster {
	 int LP = 0;
	 int random = 0;
	 int fullLP = 0;
	 int ATK = 0;
	 int x = 0;
	 int y = 0;
	String name = "";
	 boolean alive = true;
	public Monster() {
		
	}
	
	public int attack() {
		return ATK;
	}
	public void getAttacked(int strength) {
		LP = LP -strength;
		 if(LP < strength) {
			LP = 0;
			die();
		}
	}
	public void die() {
		alive = false;
	}
	public void setLP(int LP) {
		this.LP = LP;
	}
	public int getLP() {
		return LP;
	}
	public void setFullLP(int fullLP) {
		this.fullLP = fullLP;
	}
	public int getFullLP() {
		return fullLP;
	}
	public void setATK(int ATK) {
		this.ATK = ATK;
	}
	public int getATK() {
		return ATK;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setStatus(boolean alive) {
		this.alive = alive;
	}
	public boolean getStatus() {
		return alive;
	}
	public void setRandom(int random) {
		this.random = random;
	}
	public int getRandom() {
		return random;
	}
}
