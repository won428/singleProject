package com.itgroup.bean;

public class Users {
    private String id; // 유저 아이디 프라이머리 키
    private String password; // 유저 비밀번호 not null
    private String name; // 유저 닉네임 not null
    private int hp; // 유저 hp default 100
    private int exp; // 유저 경험치 default 0
    private int dmg; //  유저 데미지 default 1;
    private int lv; // 유저 레벨 default 1;


    public Users(String id, String password, String name, int hp, int exp, int dmg, int lv) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.hp = hp;
        this.exp = exp;
        this.dmg = dmg;
        this.lv = lv;

    }

    public Users() {
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public String getId() {
        return id;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", exp=" + exp +
                ", dmg=" + dmg +
                '}';
    }
}
