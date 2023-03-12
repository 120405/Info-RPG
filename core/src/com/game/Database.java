package com.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Database {
    private DatabaseConnector con;
    public Database() {
        con = new DatabaseConnector("", 0, "database.db", "", "");
    }
    public void exec(String e) {
        con.executeStatement(e);
    }

    public boolean doesGameExist() {
      //  exec("SELECT GameID FROM Game");
      //  return con.getCurrentQueryResult().getRowCount() != 0;
        return true;
    }

    public boolean isSaveFileEnabled() {
        //exec("SELECT SaveFilesEnabled FROM System");

       // return 1 == Integer.parseInt(con.getCurrentQueryResult().getData()[0][0]);
        return true;
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
        if(saveEnabled) {
            for (int a = 0; a < 4; a++) {
                for (int i = 0; i < 7; i++) {
                    guiItems[a][i] = new GUI_Item();

                }
                Image img = new Image(new Texture("crystal.png"));
                guiItems[a][7] = new GUI_Item(img, false);
            }
        } else {
            for (int a = 0; a < 4; a++) {
                for (int i = 0; i < 8; i++) {
                    guiItems[a][i] = new GUI_Item();

                }
            }
        }
        guiItems[0][0] = new GUI_Item(new Image(new Texture("crystal.png")), true);
        return guiItems;
    }
    public void save() {

            }
}

