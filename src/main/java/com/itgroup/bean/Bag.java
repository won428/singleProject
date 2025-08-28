package com.itgroup.bean;

public class Bag {

    private String id; // 유저와 조인
    private int itemcode; // 아이템과 조인
    private String iname;
    private int count;

    public Bag() {
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Bag(String iname){
        this.iname = iname;
    }

    public Bag(String id, int itemcode,String iname, int count) {
        this.id = id;
        this.itemcode = itemcode;
        this.iname = iname;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItemcode() {
        return itemcode;
    }

    public void setItemcode(int itemcode) {
        this.itemcode = itemcode;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "id='" + id + '\'' +
                ", itemcode=" + itemcode +
                '}';
    }
}
