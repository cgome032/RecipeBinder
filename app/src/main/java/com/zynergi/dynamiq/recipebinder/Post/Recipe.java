package com.zynergi.dynamiq.recipebinder.Post;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    // Ingredients list for Recipe
    private List<String> ingredients;
    private List<String> steps;

    public Recipe() {
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public List<String> getIngredients() {
        return this.ingredients;
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