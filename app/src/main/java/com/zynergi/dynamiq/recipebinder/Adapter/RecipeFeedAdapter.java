package com.zynergi.dynamiq.recipebinder.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.ArrayList;
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
        v = LayoutInflater.from(mContext).inflate(R.layout.activity_post_view, parent, false);
        ViewHolder vHolder = new ViewHolder(v);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recipeName.setText(mData.get(position).getId());

        //holder.recipeName.setText(mData.get(position).getRecipe().getName());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Button recipeName;

        public ViewHolder(View itemView) {
            super(itemView);

            recipeName = (Button) itemView.findViewById(R.id.button);

        }

    }
}