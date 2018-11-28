package com.zynergi.dynamiq.recipebinder.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.Post.Comment;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    Context context;
    List<Comment> comments;


    class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public CommentViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.commentTextView);
        }
    }

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_activity, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(comments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        if(comments != null) {
            return comments.size();
        }

        return 0;
    }

}
