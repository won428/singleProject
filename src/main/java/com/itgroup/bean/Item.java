package com.itgroup.bean;

public class Item {

    private String iname; // 아이템 이름 not null
    private int itemcode; // 아이템 코드 primary key
    private int price; // 아이템 가격 default 0

    public Item() {
    }

    public Item(String iname, int itemcode, int price) {
        this.iname = iname;
        this.itemcode = itemcode;
        this.price = price;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public int getItemcode() {
        return itemcode;
    }

    public void setItemcode(int itemcode) {
        this.itemcode = itemcode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
