package com.zynergi.dynamiq.recipebinder;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    // Ingredients list for Recipe
    private List<String> ingredients;

    public Recipe() {
        this.ingredients = new ArrayList<>();
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
}
