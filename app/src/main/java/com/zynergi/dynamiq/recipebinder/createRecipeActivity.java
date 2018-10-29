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

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Recipes");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        steps = new ArrayList<>();

    }

    public void addIngredients(View view){
        EditText eIngredents = (EditText) findViewById(R.id.editIngredients);
        String tmpIngredients = eIngredents.getText().toString();
        completedRecipe.addIngredient(tmpIngredients);
        eIngredents.setText("");

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
        completedRecipe.s
        completedRecipe.setSteps(steps);


    }
}
