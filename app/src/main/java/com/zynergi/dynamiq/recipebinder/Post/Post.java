package com.zynergi.dynamiq.recipebinder.Post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Post will keep track of a post made by a user. This includes keeping track of the recipe, comments and number of likes
 */

public class Post implements Serializable {
    private String recipeId;
    private Recipe recipe;
    private List<Comment> comments;
    private int likes;


    public Post() {
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public Post(String id) {
        this.recipeId = id;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public Post(Recipe recipe) {
        this.recipe = recipe;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public Post(Recipe recipe, String recipeId) {
        this.recipe = recipe;
        this.recipeId = recipeId;
        this.comments = new ArrayList<>();
    }

    //doing this to try to make a deep copy
    public Post(Post post) {
        this.recipeId = post.getRecipeId();
        this.likes = post.getLikes();
        this.comments = post.getComments();
        this.recipe = post.getRecipe();
    }
    public String getRecipeId() {
        return recipeId;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
