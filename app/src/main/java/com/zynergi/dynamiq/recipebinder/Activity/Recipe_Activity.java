package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;
import com.zynergi.dynamiq.recipebinder.Post.MyAdapter;
import com.zynergi.dynamiq.recipebinder.Post.Recipe;
import com.zynergi.dynamiq.recipebinder.Post.StepsAdapter;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.Character.toUpperCase;

public class Recipe_Activity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private RecyclerView mRecyclerViewIngredients;
    private MyAdapter mAdapterIngredients;
    private RecyclerView.LayoutManager mLayoutManagerIngredients;
    private static final String TAG = "firestore";
    private RecyclerView mRecyclerViewSteps;
    private StepsAdapter mAdapterSteps;
    private RecyclerView.LayoutManager mLayoutManagerSteps;

    private List<String> ingredientList;
    private List<String> stepList;
    private Context mContext;
    private TextView rname;
    private TextView ingredients_title;
    private TextView steps_title;
    private static String recipeName;
    private Map<String, Object> data;
    public static Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_);

        mContext = getApplicationContext();
        rname = findViewById(R.id.recipe_name);
        steps_title = findViewById(R.id.steps_title);
        ingredients_title = findViewById(R.id.ingredients_title);
        ingredientList = new ArrayList<>();
        stepList = new ArrayList<>();


        DocumentReference docRef = db.collection("recipes").document("acfOtTxeAGMcKAcYWDip");
        data = new HashMap<>();
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    recipeName = document.getData().get("name").toString();
                    recipeName = recipeName.toUpperCase();
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    rname.setText(recipeName);
                    ingredients_title.setText("Ingredients:");
                    steps_title.setText("Steps:");
                    ingredientList = (List<String>) document.get("ingredients");
                    stepList = (List<String>) document.get("steps");
                    System.out.println("The ID:" + document.getId());
                    if (ingredientList.isEmpty())
                        System.out.println("ITS EMPTY");
                    //System.out.println("WHAT THE FUCK "+recipeName);

                    mRecyclerViewIngredients = (RecyclerView) findViewById(R.id.ingredient_list);
                    mLayoutManagerIngredients = new LinearLayoutManager(getApplicationContext());
                    mRecyclerViewIngredients.setHasFixedSize(true);
                    mRecyclerViewIngredients.setLayoutManager(mLayoutManagerIngredients);
                    mAdapterIngredients = new MyAdapter(ingredientList, mContext);
                    mRecyclerViewIngredients.setAdapter(mAdapterIngredients);

                    mRecyclerViewSteps = (RecyclerView) findViewById(R.id.recipe_steps);
                    mLayoutManagerSteps = new LinearLayoutManager(getApplicationContext());
                    mRecyclerViewSteps.setLayoutManager(mLayoutManagerSteps);
                    mAdapterSteps = new StepsAdapter(stepList, mContext);
                    mRecyclerViewSteps.setAdapter(mAdapterSteps);
                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
            }
        }
    );
        /*
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            if (task.isSuccessful()){
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot != null){
                    recipe = documentSnapshot.toObject(Recipe.class);
                }
            }

            }
        });
        */




//        System.out.println((String) data.get("name").toString());
    //    ingredientList = recipe.getIngredients();
//        stepList = recipe.getSteps();
/*
        for (int i = 0; i < stepList.size();i++)
        {
            System.out.println("This is an ingredient: " + i + stepList.get(i));
        }
*/




        /*

        TextView Recipe_Name = findViewById(R.id.recipe_name);


        RecyclerView rvIngredients = (RecyclerView) findViewById(R.id.ingredient_list);
        RecyclerView rvSteps = (RecyclerView) findViewById(R.id.recipe_steps);


        //Initialize Recipe, retrieve data from recipe
        //use Intent to get Recipe
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");

        Recipe_Name.setText(recipe.getName());

        List<String> ing_list = recipe.getIngredients();
        List<String> step_list = recipe.getSteps();

        StepsAdapter Iadapter = new StepsAdapter(ing_list);
        StepsAdapter Sadapter = new StepsAdapter(step_list);

        rvIngredients.setAdapter(Iadapter);
        rvSteps.setAdapter(Sadapter);

        rvIngredients.setLayoutManager(new LinearLayoutManager(this));
        rvSteps.setLayoutManager(new LinearLayoutManager(this));
            */
    }
/*
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
    public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

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
        public StepsAdapter(List<String> ingredients) {
            ingredientList = ingredients;
        }

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview

        // Usually involves inflating a layout from XML and returning the holder
        @Override
        public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        public void onBindViewHolder(StepsAdapter.ViewHolder viewHolder, int position) {
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
*/
}

