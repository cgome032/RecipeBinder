package com.zynergi.dynamiq.recipebinder.Post;

/**
 * Comment class will keep track of a specific comment made on a post identified by the postid member
 */

public class Comment {
    private long uid; //id of the user posting comment
    private long postid; //id of the post that this is attached to
    private long id; //id of the comment
    private String comment;

    public Comment(long uid, long postid, long id) {
        this.uid = uid;
        this.postid = postid;
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public long getUid() {
        return uid;
    }

    public long getId() {
        return id;
    }

    public long getPostid() {
        return postid;
    }
}
