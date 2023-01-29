package com.game.database;

import com.game.Spiel;
import com.game.items.GUI_Item;

public class Database {
	private DatabaseConnector con;
	public int gameID = 0;
	
	public Database() {
		con = new DatabaseConnector("",0,"database.db","","");
			gameID = 0;
		
	}
	public GUI_Item[][] loadItems(GUI_Item[][] items, boolean enabled) {
		if(enabled) {
		exec("SELECT TableID,Name,Durability,GameID FROM Items");
		QueryResult qr = con.getCurrentQueryResult();
		for(int e = 0; e < qr.getRowCount(); e++) {
		for(int a = 0; a < 4; a++) {
			for(int i = 0; i < 8; i++) {
			if((i + a*8) == Integer.parseInt(qr.getData()[e][0]) && Integer.parseInt(qr.getData()[e][3]) == gameID) {
				items[a][i] = new GUI_Item(qr.getData()[e][1],Integer.parseInt(qr.getData()[e][2]));
			}
				if(items[a][i] == null) {
					items[a][i] = new GUI_Item();
				}
			}
			}
		}
		exec("SELECT Money,Health,GameID FROM STATUS");
		QueryResult qr2 = con.getCurrentQueryResult();
		for(int i = 0; i < qr2.getRowCount(); i++) {
		if(gameID == Integer.parseInt(qr2.getData()[0][2])) {
			Spiel.INSTANCE.setMoney(Integer.parseInt(qr2.getData()[0][0]));
			Spiel.INSTANCE.setHeroLP(Integer.parseInt(qr2.getData()[0][1]));
		}
		}
		} else {
			for(int a = 0; a < 4; a++) {
				for(int i = 0; i < 8; i++) {
					if(items[a][i] == null) {
						items[a][i] = new GUI_Item();	
				}
				}
		}
		}
        return items;
		
	}
	public void save(GUI_Item[][] items, int healthHero, int healthMonster, int money) {
		for(int a = 0; a < 4; a++) {
		for(int i = 0; i < 8; i++) {
			if(items != null && items[a][i].doesItemExist()) {
				exec("UPDATE Items set TableID = " + "'"+ (i + a*8) +"'" +" WHERE Name = " + "'" + items[a][i].getName()+ "'");	
					exec("UPDATE Items set GameID = " + "'"+ gameID +"'" +" WHERE Name = " + "'" + items[a][i].getName()+ "'");
			}
		}
		}
		exec("UPDATE Status set Health = "+ "'" + healthHero + "'" + ",Money = " + "'" + money + "',GameID = '"+gameID+"'");
		
	}
	public void exec(String e) {
		con.executeStatement(e);
	}
	public boolean doesGameExist() {
		exec("SELECT GameID FROM Game");
		QueryResult qr = con.getCurrentQueryResult();
		if(qr.getRowCount() != 0) {
			return  true;
		} else {
			return false;
		}
		
	}
	public boolean isSaveFileEnabled() {
		exec("SELECT SaveFilesEnabled FROM System");
		QueryResult qr = con.getCurrentQueryResult();
		return 1 == Integer.parseInt(qr.getData()[0][0]);
	}
	public void saveOptions() {
		int save = 0;
		if(Spiel.INSTANCE.saveEnabled) {
			save = 1;
		}
		exec("UPDATE System set SaveFilesEnabled = " + "'" + save + "'");	
	}
	public void setGameID(int id) {
		gameID = id;
	}
}