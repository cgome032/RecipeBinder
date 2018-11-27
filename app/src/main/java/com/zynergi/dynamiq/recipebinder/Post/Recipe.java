package com.zynergi.dynamiq.recipebinder.Post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {

    // Ingredients list for Recipe
    private String name;
    private String postID;
    private List<String> ingredients;
    private List<String> steps;

    public Recipe() {
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    //trying to get the deep copy
    public Recipe(Recipe recipe) {
        this.name = recipe.getName();
        this.ingredients = recipe.getIngredients();
        this.steps = recipe.getSteps();
    }

    public String getName(){return this.name;}

    public String getPostID(){return this.postID;}

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPostID(String mpostID){
        this.postID = mpostID;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
