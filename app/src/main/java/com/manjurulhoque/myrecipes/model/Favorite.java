package com.manjurulhoque.myrecipes.model;

public class Favorite {

    private String category_name;
    private String category_image_url;
    private String recipe_name;
    private String recipe_description;
    private String recipe_image_url;

    public Favorite() {
    }

    public Favorite(String category_name, String category_image_url, String recipe_name, String recipe_description, String recipe_image_url) {
        this.category_name = category_name;
        this.category_image_url = category_image_url;
        this.recipe_name = recipe_name;
        this.recipe_description = recipe_description;
        this.recipe_image_url = recipe_image_url;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image_url() {
        return category_image_url;
    }

    public void setCategory_image_url(String category_image_url) {
        this.category_image_url = category_image_url;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_description() {
        return recipe_description;
    }

    public void setRecipe_description(String recipe_description) {
        this.recipe_description = recipe_description;
    }

    public String getRecipe_image_url() {
        return recipe_image_url;
    }

    public void setRecipe_image_url(String recipe_image_url) {
        this.recipe_image_url = recipe_image_url;
    }
}
