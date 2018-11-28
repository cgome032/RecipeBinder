package com.zynergi.dynamiq.recipebinder.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

public class PostAdapter extends FirestoreRecyclerAdapter<Post, PostAdapter.PostHolder> {
    public PostAdapter(@NonNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder( @NonNull PostHolder holder, int position, @NonNull Post model) {
        holder.recipeName.setText(model.getRecipe().getName());
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_post_view, parent, false);
        return new PostHolder(v);
    }

    public static class PostHolder extends RecyclerView.ViewHolder {
        private Button recipeName;

        public PostHolder(View itemView) {
            super(itemView);

            recipeName = itemView.findViewById(R.id.button);
        }
    }
}
