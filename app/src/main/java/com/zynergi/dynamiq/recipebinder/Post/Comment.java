package com.zynergi.dynamiq.recipebinder.Post;

import java.io.Serializable;

/**
 * Comment class will keep track of a specific comment made on a post identified by the postid member
 */

public class Comment implements Serializable {
    private String postId;
    private String comment;

    public Comment() {}

    public Comment(String postId, String comment) {
        this.postId = postId;
        this.comment = comment;
    }

    public String getPostId() {
        return this.postId;
    }

    public String getComment() {
        return comment;
    }
}
