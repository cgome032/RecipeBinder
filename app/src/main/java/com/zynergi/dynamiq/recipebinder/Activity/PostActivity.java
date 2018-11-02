package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

public class PostActivity extends AppCompatActivity {

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        //when passing intent put the post under the key "Post"
        this.post = (Post) getIntent().getSerializableExtra("Post");
    }

    public void displayRecipe(View view) {
        //TODO: Integrate with recipe activity
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("Recipe", post.getRecipe());
        startActivity(intent);
    }

    public void displayComments(View view) {
        //TODO: Comment activity
        Intent intent = new Intent(this, CommentActivity.class);
        //passing the post instead of the comments for now not the most elegant solution but it works
        intent.putExtra("Post", post);
        startActivity(intent);
    }
}
