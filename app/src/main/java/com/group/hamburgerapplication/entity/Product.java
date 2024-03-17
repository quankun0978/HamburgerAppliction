package com.group.hamburgerapplication.entity;

public class Product {
    private String productId,name,description,category ,path;
    private int price;

    public Product() {
    }

    public Product(String productId, String name, String description, String category, String path, int price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.path = path;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
