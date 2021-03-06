package com.zynergi.dynamiq.recipebinder.Post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Post will keep track of a post made by a user. This includes keeping track of the recipe, comments and number of likes
 */

public class Post implements Serializable {
    private String id;
    private String recipeId;
    private Recipe recipe;
    private List<Comment> comments;
    private List<String> uids = new ArrayList<>();


    public Post() {
        this.comments = new ArrayList<>();
    }

    public Post(String recipeId) {
        this.recipeId = recipeId;
        this.comments = new ArrayList<>();
    }

    public Post(Recipe recipe) {
        this.recipe = recipe;
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
        this.comments = post.getComments();
        this.recipe = post.getRecipe();
        this.uids = post.getUids();
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUids() {
        return uids;
    }

    public void setUids(List<String> uids) {
        this.uids = uids;
    }

    public void addUid(String uid) {

        boolean repeat = false;

        for (int i = 0; i < uids.size(); i++) {
            if (uid.equals(uids.get(i))) {
                repeat = true;
            }
        }

        if (!repeat) {
            this.uids.add(uid);
        }
    }
}
