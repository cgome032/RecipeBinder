package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;
import com.zynergi.dynamiq.recipebinder.Recipe_Activity;

public class PostActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Post post;
    private Button recipeButton;
    private String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        //when passing intent put the post under the key "Post"
        post = (Post) getIntent().getSerializableExtra("Post");
        recipeName = (String) getIntent().getSerializableExtra("RecipeName");
        recipeButton = findViewById(R.id.recipeButton);
        recipeButton.setText(recipeName);
    }

    public void displayRecipe(View view) {
        //TODO: Integrate with recipe activity
        Intent intent = new Intent(this, Recipe_Activity.class);
        intent.putExtra("RecipeId", post.getRecipeId());
        startActivity(intent);
    }

    public void displayComments(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        //passing the post instead of the comments for now not the most elegant solution but it works
        intent.putExtra("Post", post);
        startActivity(intent);
    }

    public void favorite(View view) {
        //post.setLikes(post.getLikes() + 1);


    }
}
