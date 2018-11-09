package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zynergi.dynamiq.recipebinder.Post.Comment;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.List;

public class AddCommentActivity extends AppCompatActivity {

    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        post = (Post) getIntent().getSerializableExtra("Post");
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
    }

}
