package com.zynergi.dynamiq.recipebinder.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.zynergi.dynamiq.recipebinder.Activity.PostActivity;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

public class PostAdapter extends FirestoreRecyclerAdapter<Post, PostAdapter.PostHolder> {
    public PostAdapter(@NonNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostHolder holder, int position, @NonNull final Post model) {
        holder.recipeName.setText(model.getRecipe().getName());

        holder.recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.Parent.getContext(), PostActivity.class);
                intent.putExtra("Post", model);
                intent.putExtra("RecipeName", model.getRecipe().getName());
                intent.putExtra("RecipeId", model.getRecipeId());
                //Log.d("FeedAdapter", "Recipe Id " + mData.get(position).getRecipeId());
                holder.Parent.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedcard, parent, false);
        return new PostHolder(v, parent);
    }

    public static class PostHolder extends RecyclerView.ViewHolder {
        private TextView recipeName;
        private CardView recipeCard;
        public ViewGroup Parent;

        public PostHolder(View itemView, ViewGroup parent) {
            super(itemView);

            Parent = parent;
            recipeName = itemView.findViewById(R.id.cardText);
            recipeCard = itemView.findViewById(R.id.postCard);
        }
    }
}
