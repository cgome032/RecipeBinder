package com.zynergi.dynamiq.recipebinder.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.Activity.PostActivity;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.Post.Recipe;
import com.zynergi.dynamiq.recipebinder.R;
import com.zynergi.dynamiq.recipebinder.Recipe_Activity;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class RecipeFeedAdapter extends RecyclerView.Adapter<RecipeFeedAdapter.ViewHolder> {

    Context mContext;
    List<Post> mData;

    public RecipeFeedAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedcard, parent, false);
        ViewHolder vHolder = new ViewHolder(v);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //holder.recipeName.setText(mData.get(position).getRecipeId());

        holder.cardText.setText(mData.get(position).getRecipeId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("Post", mData.get(position));
                intent.putExtra("RecipeName", mData.get(position).getRecipe().getName());
                intent.putExtra("RecipeId", mData.get(position).getRecipeId());
                Log.d("FeedAdapter", "Recipe Id " + mData.get(position).getRecipeId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

        private TextView cardText = itemView.findViewById(R.id.cardText);

       /* private Button recipeName;
        private Button comments;
        private Button favorite;
        */
        public ViewHolder(View itemView) {
            super(itemView);

        }

    }


}