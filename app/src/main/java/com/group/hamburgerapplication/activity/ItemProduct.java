package com.group.hamburgerapplication.activity;

public class ItemProduct {
    private String name,describe,price;
    private int img,edit,delete;

    public ItemProduct(String name, String describe, String price, int img, int edit, int delete) {
        this.name = name;
        this.describe = describe;
        this.price = price;
        this.img = img;
        this.edit = edit;
        this.delete = delete;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public String getPrice() {
        return price;
    }

    public int getImg() {
        return img;
    }

    public int getEdit() {
        return edit;
    }

    public int getDelete() {
        return delete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}
