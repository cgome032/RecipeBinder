package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.Adapter.CommentAdapter;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;


public class CommentActivity extends AppCompatActivity {

    private Post post;
    private RecyclerView recyclerView;
    private CommentAdapter commentAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    TextView commentsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        post = (Post) getIntent().getSerializableExtra("Post");
        context = getApplicationContext();
        commentsView = findViewById(R.id.commentTextView);

        recyclerView = findViewById(R.id.commentRecycler);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(post, context);
        recyclerView.setAdapter(commentAdapter);
    }
}
