package com.zynergi.dynamiq.recipebinder.Profile;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    // Profile name
    private String name;
    private String description;
    private List<String> recipeNames;
    private List<String> favoriteRecipes;

    public Profile() {
        this.name = "";
        this.description = "";
        this.recipeNames = new ArrayList<>();
        this.favoriteRecipes = new ArrayList<>();
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }

    public void setRecipeNames(List<String> recipeNames) { this.recipeNames = recipeNames; }

    public List<String> getFavoriteRecipes() { return favoriteRecipes; }

    public void setFavoriteRecipes(List<String> favoriteRecipes) { this.favoriteRecipes = favoriteRecipes; }

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

    public void addRecipeName(String recipeName) { this.recipeNames.add(recipeName); }

    public void addFavoriteRecipe(String favoriteName) { this.favoriteRecipes.add(favoriteName); }
}
