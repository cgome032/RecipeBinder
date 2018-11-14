package com.zynergi.dynamiq.recipebinder.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.Post.MyAdapter;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.ArrayList;
import java.util.List;

public class recipesAdapter extends RecyclerView.Adapter<recipesAdapter.MyViewHolder> {

    List<String> myRecipes;
    Context mContext;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.ingredient);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)

    public recipesAdapter(List<String> myDataset, Context context) {

        this.myRecipes = myDataset;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list, parent, false);

        return new MyViewHolder(view);
        /*
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
*/
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(myRecipes.get(position));
        System.out.println("Word is: " + myRecipes.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (myRecipes != null) {
            System.out.println("I have this many: " + myRecipes.size());
            return myRecipes.size();
        }
        return 0;
    }
}
