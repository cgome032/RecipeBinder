package com.zynergi.dynamiq.recipebinder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zynergi.dynamiq.recipebinder.Post.Recipe;

import java.util.List;

public class Recipe_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_);

        TextView Recipe_Name = findViewById(R.id.recipe_name);


        RecyclerView rvIngredients = (RecyclerView) findViewById(R.id.ingredient_list);
        RecyclerView rvSteps = (RecyclerView) findViewById(R.id.recipe_steps);


        //Initialize Recipe, retrieve data from recipe
        //use Intent to get Recipe
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");

        Recipe_Name.setText(recipe.getName());

        List<String> ing_list = recipe.getIngredients();
        List<String> step_list = recipe.getSteps();

        IngredientAdapter Iadapter = new IngredientAdapter(ing_list);
        StepsAdapter Sadapter = new StepsAdapter(step_list);

        rvIngredients.setAdapter(Iadapter);
        rvSteps.setAdapter(Sadapter);

        rvIngredients.setLayoutManager(new LinearLayoutManager(this));
        rvSteps.setLayoutManager(new LinearLayoutManager(this));
    }

    public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

        private List<String> stepsList;


        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            private TextView stepsTextView;

            // Pass in the contact array into the constructor


            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

                stepsTextView = itemView.findViewById(R.id.recipe_steps);
            }
        }

        public StepsAdapter(List<String> steps) {
            stepsList = steps;
        }

        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View stepView = inflater.inflate(R.layout.item_steps, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(stepView);
            return viewHolder;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(StepsAdapter.ViewHolder viewHolder, int position) {
            // Get the data model based on position
            String ingredient = stepsList.get(position);

            // Set item views based on your views and data model
            TextView textView = viewHolder.stepsTextView;
            textView.setText(ingredient);
        }

        @Override
        public int getItemCount() {
            return stepsList.size();
        }
    }
    public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

        private List<String> ingredientList;

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row


            public TextView ingredientTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                ingredientTextView = itemView.findViewById(R.id.ingredient_name);
            }
        }
            // Pass in the contact array into the constructor
        public IngredientAdapter(List<String> ingredients) {
            ingredientList = ingredients;
        }

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public IngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View ingredientView = inflater.inflate(R.layout.item_ingredient, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(ingredientView);
            return viewHolder;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(IngredientAdapter.ViewHolder viewHolder, int position) {
            // Get the data model based on position
            String ingredient = ingredientList.get(position);

            // Set item views based on your views and data model
            TextView textView = viewHolder.ingredientTextView;
            textView.setText(ingredient);
        }

        @Override
        public int getItemCount() {
            return ingredientList.size();
        }


    }
    //Recipe ID grab data
    //Recipe name
    //Recipe Ingredient List
    //Recipe Instructions List
}
