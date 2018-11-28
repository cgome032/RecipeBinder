package com.zynergi.dynamiq.recipebinder.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.R;

import java.util.List;

import javax.annotation.Nullable;

public class createRecipeAdapter extends RecyclerView.Adapter<createRecipeAdapter.createRecipeViewHolder> {

    Context context;
    List<String> strings;


    class createRecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public createRecipeViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.commentTextView);
            
        }
    }

    public createRecipeAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public createRecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_activity, parent, false);
        return new createRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(createRecipeViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        if(strings != null) {
            return strings.size();
        }

        return 0;
    }

}
