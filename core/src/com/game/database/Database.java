package com.game.database;

import com.game.Spiel;
import com.game.items.GUI_Item;

public class Database {
	private DatabaseConnector con;
	public int gameID = 0;

	public Database() {
		con = new DatabaseConnector("", 0, "database.db", "", "");
		gameID = 0;
	}

	public GUI_Item[][] loadItems(GUI_Item[][] items, boolean enabled) {
		if (enabled) {
			exec("SELECT TableID,Name,Durability FROM Items WHERE GameID = '" + gameID + "'");
			QueryResult qr = con.getCurrentQueryResult();
			int count = qr.getRowCount();
			if (count == 0) {
				count++;
			}
			for (int e = 0; e < count; e++) {
				for (int a = 0; a < 4; a++) {
					for (int i = 0; i < 8; i++) {
						if (qr.getRowCount() != 0 && (i + a * 8) == Integer.parseInt(qr.getData()[e][0])) {
							items[a][i] = new GUI_Item(qr.getData()[e][1], Integer.parseInt(qr.getData()[e][2]));
						}
						if (items[a][i] == null) {
							items[a][i] = new GUI_Item();
						}
					}
				}
			}
			exec("SELECT Money,Health FROM STATUS WHERE GameID = '" + gameID + "'");
			QueryResult qr2 = con.getCurrentQueryResult();

			if (qr2.getRowCount() != 0) {
				Spiel.INSTANCE.setMoney(Integer.parseInt(qr2.getData()[0][0]));
				Spiel.INSTANCE.setHeroLP(Integer.parseInt(qr2.getData()[0][1]));
			} else {
				Spiel.INSTANCE.setMoney(1000);
				Spiel.INSTANCE.setHeroLP(100);
			}
		} else {
			for (int a = 0; a < 4; a++) {
				for (int i = 0; i < 8; i++) {
					if (items[a][i] == null) {
						items[a][i] = new GUI_Item();
					}
				}
			}
		}
		return items;

	}

	public void save(GUI_Item[][] items, int healthHero, int healthMonster, int money) {
		for (int a = 0; a < 4; a++) {
			for (int i = 0; i < 8; i++) {
				if (items != null && items[a][i].doesItemExist()) {
					exec("UPDATE Items set TableID = " + "'" + (i + a * 8) + "',GameID = '" + gameID +"'" + " WHERE Name = " + "'"
							+ items[a][i].getName() + "'");
				}
			}
		}
		exec("SELECT GameID From Status WHERE GameID = '"+ gameID +"'");
		
		if(con.getCurrentQueryResult() != null && con.getCurrentQueryResult().getRowCount() != 0 ) {
			exec("UPDATE Status set Health = " + "'" + healthHero + "'" + ",Money = " + "'" + money + "' WHERE GameID = '"+ gameID +"'");	
		} else if(Spiel.INSTANCE.ngame) {
			exec("INSERT INTO Status (Health, Money, GameID) VALUES ('"+ healthHero +"', '"+money+"', '"+gameID+"')");
		}
		
		if(!doesGameExist() && Spiel.INSTANCE.ngame) {
			exec("INSERT INTO GAME (GameID, Name) VALUES ('1', '') ");
		} else if(Spiel.INSTANCE.lgame) {
	 exec("UPDATE Game set GameID = '"+ (gameID) + "'"); 
		}
	}

	public void exec(String e) {
		con.executeStatement(e);
	}

	public boolean doesGameExist() {
		exec("SELECT GameID FROM Game");
		return con.getCurrentQueryResult().getRowCount() != 0;
	}
	public boolean doesGameExist(int gameID1) {
		exec("SELECT GameID FROM Game WHERE GameID = '"+ gameID1 +"'");
		return !(con.getCurrentQueryResult() == null) && con.getCurrentQueryResult().getRowCount() != 0;
	}
	public boolean isSaveFileEnabled() {
		exec("SELECT SaveFilesEnabled FROM System");
		
		return 1 == Integer.parseInt(con.getCurrentQueryResult().getData()[0][0]);
	}

	public void saveOptions() {
		int save = 0;
		if (Spiel.INSTANCE.saveEnabled) {
			save = 1;
		}
		exec("UPDATE System set SaveFilesEnabled = " + "'" + save + "'");
		con.close();
	}

	public void setGameID(int id) {
		gameID = id;
	}
}