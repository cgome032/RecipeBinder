package com.zynergi.dynamiq.recipebinder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


}
