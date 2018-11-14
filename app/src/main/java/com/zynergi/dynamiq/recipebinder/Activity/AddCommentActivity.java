package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zynergi.dynamiq.recipebinder.Post.Comment;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.List;

public class AddCommentActivity extends AppCompatActivity {

    private static final String COLLECTION = "posts";
    private static final String TAG = "firestore";

    private Post post;

    private FirebaseFirestore db;
    private boolean updated; //flag to see if the post has been updated and should be pushed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        post = (Post) getIntent().getSerializableExtra("Post");
        db = FirebaseFirestore.getInstance();
        updated = false;
    }

    public void uploadComment(View view) {
        EditText editText = findViewById(R.id.commentText);
        String commentText = editText.getText().toString();

        if(commentText.isEmpty()) {
            Context context = getApplicationContext();
            Toast.makeText(context, "Please enter a comment", Toast.LENGTH_SHORT).show();
            return;
        }

        Comment comment= new Comment(commentText);

        post.addComment(comment);
        updated = true;
    }

    @Override
    public void onBackPressed() {
        if(updated) {
            DocumentReference documentReference = db.collection(COLLECTION).document(post.getId());
            documentReference.update("comments", post.getComments())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Post with id" + post.getId() + " comments updated");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error editing post " + post.getId());
                        }
                    });

        }
    }

}
