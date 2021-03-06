package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.Profile.Profile;
import com.zynergi.dynamiq.recipebinder.R;
import com.zynergi.dynamiq.recipebinder.Recipe_Activity;

public class PostActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Post post;
    private Button recipeButton;
    private String recipeName;
    private String recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        //when passing intent put the post under the key "Post"
        post = (Post) getIntent().getSerializableExtra("Post");
        recipeName = (String) getIntent().getSerializableExtra("RecipeName");
        recipeId = (String) getIntent().getSerializableExtra("RecipeId");
        recipeButton = findViewById(R.id.recipeButton);
        recipeButton.setText(recipeName);
    }

    public void displayRecipe(View view) {
        Intent intent = new Intent(this, Recipe_Activity.class);
        intent.putExtra("RecipeId", post.getRecipeId());
        startActivity(intent);
    }

    public void displayComments(View view) {
        Intent intent = new Intent(this, CommentActivity.class);
        //passing the post instead of the comments for now not the most elegant solution but it works
        intent.putExtra("PostToComment", post);
        intent.putExtra("RecipeId", recipeId);
        startActivity(intent);
    }

    public void favorite(View view) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        final String uid = auth.getCurrentUser().getUid();

        post.addUid(uid);

        Log.d("favoriteId ", post.getId());

        db.collection("posts").document(post.getId()).update("uids", post.getUids());

        /*DocumentReference documentReference = db.collection("profiles").document(uid);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Profile currentUser = documentSnapshot.toObject(Profile.class);

                currentUser.addFavoriteRecipe(post.getRecipe().getName());

                db.collection("profiles").document(uid).update("favoriteRecipes",currentUser.getFavoriteRecipes());
            }
        });*/

    }
}
