package com.zynergi.dynamiq.recipebinder.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    Post post;
    Context context;


    class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public CommentViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.commentTextView);
        }
    }

    public CommentAdapter(Post post, Context context) {
        this.post = post;
        this.context = context;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_activity, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder viewHolder, int position) {
        viewHolder.textView.setText(post.getComments().get(position).getComment());
    }

    @Override
    public int getItemCount() {
        if(post.getComments() != null) {
            return post.getComments().size();
        }

        return 0;
    }

}
