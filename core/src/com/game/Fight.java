package com.game;

public class Fight {
    private String winner = "";

    public Fight() {

    }

    public String fight(Monster monster, Hero hero) {
        if (monster.getStatus() && hero.getStatus()) {
            monster.getAttacked(hero.attack());
            hero.getAttacked(hero.attack());
        } else {
            if (!monster.getStatus()) {
                winner = "Hero";

            } else if (!hero.getStatus()) {
                winner = "Monster";

            }
        }
        return winner;
    }
}
