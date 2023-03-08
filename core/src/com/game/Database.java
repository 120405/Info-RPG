package com.game;


public class Database {
    private DatabaseConnector con;
    public Database() {
        con = new DatabaseConnector("", 0, "database.db", "", "");
    }
    public void exec(String e) {
        con.executeStatement(e);
    }

    public boolean doesGameExist() {
        exec("SELECT GameID FROM Game");
        return con.getCurrentQueryResult().getRowCount() != 0;
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

    public GUI_Item[][] loadItems(GUI_Item[][] guiItems, boolean saveEnabled) {
        return null;
    }
}

