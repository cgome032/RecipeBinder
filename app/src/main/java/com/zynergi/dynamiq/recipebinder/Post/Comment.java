package com.zynergi.dynamiq.recipebinder.Post;

import java.io.Serializable;

/**
 * Comment class will keep track of a specific comment made on a post identified by the postid member
 */

public class Comment implements Serializable {
    private String comment;

    public Comment(String  comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
