package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import android.view.View;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.zynergi.dynamiq.recipebinder.Adapter.CommentAdapter;
import com.zynergi.dynamiq.recipebinder.Post.Comment;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.List;


public class CommentActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private Post post;
    private String recipeId;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    TextView commentsView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        post = (Post) getIntent().getSerializableExtra("PostToComment");
        recipeId = (String) getIntent().getSerializableExtra("RecipeId");
        context = getApplicationContext();
        commentsView = findViewById(R.id.commentTextView);

        db = FirebaseFirestore.getInstance();

        getComments(new CommentCallBack() {
            @Override
            public void onCallBack(List<Comment> comments) {
                recyclerView = findViewById(R.id.commentRecycler);
                layoutManager = new LinearLayoutManager(context);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
                commentAdapter = new CommentAdapter(context, comments);
                recyclerView.setAdapter(commentAdapter);
            }
        });
    }

    public void addComment(View view) {
        /*Intent intent = new Intent(this, AddCommentActivity.class);
        intent.putExtra("Post", post);
        startActivity(intent);*/

        EditText editText = findViewById(R.id.addCommentEditText);
        String commentText = editText.getText().toString();

        if(commentText.isEmpty()) {
            Context context = getApplicationContext();
            Toast.makeText(context, "Please enter a comment", Toast.LENGTH_SHORT).show();
            return;
        }

        Comment comment = new Comment(post.getRecipe().getPostID(), commentText);
        editText.setText("");

        post.addComment(comment);

        db.collection("comments").add(comment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("Comments: ", "added comment with id " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Comments: ", "failed to upload comment");
            }
        });
    }

    public interface CommentCallBack {
        void onCallBack(List<Comment> comments);
    }

    private void getComments(final CommentCallBack commentCallBack) {
        CollectionReference comments = db.collection("comments");

        comments.whereEqualTo("postId", post.getRecipe().getPostID()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Comment> postComments = queryDocumentSnapshots.toObjects(Comment.class);
                commentCallBack.onCallBack(postComments);
            }
        });
    }
}
