package com.zynergi.dynamiq.recipebinder.Post;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Post will keep track of a post made by a user. This includes keeping track of the recipe, comments and number of likes
 */

public class Post implements Serializable {
    private long uid; //user id associated wit account making the post (made a long for now just a place holder we can talk about it later)
    private long id; //id of the post in the database
    private Recipe recipe;
    private byte[] image;
    private List<Comment> comments;
    private int likes;


    public Post (long uid, long id) {
        this.uid = uid;
        this.id = id;
        likes = 0;
        comments = new ArrayList<>();
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
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

    public long getId() {
        return id;
    }

    public long getUid() {
        return uid;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
