package com.itgroup.bean;

public class Monster {

    private int monstercode; //몬스터 코드 primary key
    private String mname; // 몬스터 이름 not null,
    private int hp;
    private int item; // varchar2(20),
    private int exp; // number default 1,
    private int dmg; // number default 1

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Monster(int monstercode, String mname, int hp, int item, int exp, int dmg) {
        this.monstercode = monstercode;
        this.mname = mname;
        this.hp = hp;
        this.item = item;
        this.exp = exp;
        this.dmg = dmg;
    }

    public Monster() {
    }

    public int getMonstercode() {
        return monstercode;
    }

    public void setMonstercode(int monstercode) {
        this.monstercode = monstercode;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Monster(int hp) {
        this.hp = hp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}
