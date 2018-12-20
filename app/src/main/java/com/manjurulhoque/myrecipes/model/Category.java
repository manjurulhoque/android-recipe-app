package com.manjurulhoque.myrecipes.model;

public class Category {

    public String key;
    public String name;
    public String imageUrl;

    public Category() {
    }

    public Category(String key, String name, String imageUrl) {
        this.key = key;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
