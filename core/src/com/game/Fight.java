package com.game;

public class Fight {
    private String winner = "";
    private final Monster monster;
    private final Hero hero;

    public Fight(int mLP,int mfullLP, int mATK, String mname, int mtype, int hLP, int hfullLP, int hATK, String hname) {
        monster = new Monster(mLP, mfullLP, mATK, mname, mtype);
        hero = new Hero(hLP, hfullLP, hATK, hname);
    }

    public String fight() {
        if (monster.getStatus() && hero.getStatus()) {
            monster.getAttacked(hero.attack(), hero.getEffectRd(), hero.getSkillRd());
            hero.getAttacked(monster.attack(), monster.getEffectRd(), monster.getSkillRd());
        }
            if (!monster.getStatus()) {
                winner = "Hero";

            } else if (!hero.getStatus()) {
                winner = "Monster";

            }

        return winner;
    }
    public Monster getMonster() {
        return monster;
    }
    public Hero getHero() {
        return hero;
    }
    public String getWinner() {
        return winner;
    }
    public void reset() {
        winner = "";
        hero.setStatus(true);
        monster.setStatus(true);
        monster.setLP(monster.getFullLP());
        hero.setLP(hero.getFullLP());
    }
}
