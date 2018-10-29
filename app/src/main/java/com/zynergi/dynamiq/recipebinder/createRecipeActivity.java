package com.zynergi.dynamiq.recipebinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zynergi.dynamiq.recipebinder.Post.Recipe;

import java.util.ArrayList;

public class createRecipeActivity extends AppCompatActivity {


    Recipe completedRecipe = new Recipe();
    ArrayList<String> steps;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_create_recipe);
        steps = new ArrayList<>();

    }

    public void addIngredients(View view){
        EditText eIngredients = (EditText) findViewById(R.id.editIngredients);
        String tmpIngredients = eIngredients.getText().toString();
        completedRecipe.addIngredient(tmpIngredients);
        eIngredients.setText("");

    }

    public void addSteps(View view){
        EditText eSteps = findViewById(R.id.editSteps);
        String tmpSteps = eSteps.getText().toString();
        steps.add(tmpSteps);
        eSteps.setText("");
    }

    public void submitRecipe(View view){
        EditText eName = findViewById(R.id.editName);
        String sName = eName.getText().toString();
        completedRecipe.setName(sName);
        completedRecipe.setSteps(steps);

        mDatabase.child("Recipes").setValue(completedRecipe);


    }
}
