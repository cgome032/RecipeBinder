package com.zynergi.dynamiq.recipebinder.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

public class PostActivity extends AppCompatActivity {

    private Button recipeName;
    private Button likes;

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        //when passing intent put the post under the key "Post"
        post = (Post) getIntent().getSerializableExtra("Post");

        recipeName = findViewById(R.id.recipeNameButton);

    }

    public void displayRecipe(View view) {
        //TODO: Integrate with recipe activity
        /*Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("Recipe", post.getRecipe());
        startActivity(intent);*/
    }

    public void displayComments(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        //passing the post instead of the comments for now not the most elegant solution but it works
        intent.putExtra("Post", post);
        startActivity(intent);
        this.overridePendingTransition(0,0);
    }

    public void like(View view) {
        post.setLikes(post.getLikes() + 1);
    }
}
