package com.example.foodapp.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private int imageResource;
    private String name;
    private String price;
    private String description;

    public Product(int id, int imageResource, String name, String price, String description) {
        this.id = id;
        this.imageResource = imageResource;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}

