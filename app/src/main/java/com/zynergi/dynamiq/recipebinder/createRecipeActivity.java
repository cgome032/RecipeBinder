package com.zynergi.dynamiq.recipebinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zynergi.dynamiq.recipebinder.Post.Recipe;

import java.util.ArrayList;

public class createRecipeActivity extends AppCompatActivity {


    Recipe completedRecipe = new Recipe();
    ArrayList<String> steps;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference("recipes");
        setContentView(R.layout.activity_create_recipe);
        steps = new ArrayList<>();


    }

    public void addIngredients(View view){
        EditText eIngredients = (EditText) findViewById(R.id.editIngredients);
        String tmpIngredients = eIngredients.getText().toString();
        completedRecipe.addIngredient(tmpIngredients);
        eIngredients.getText().clear();

    }

    public void addSteps(View view){
        EditText eSteps = findViewById(R.id.editSteps);
        String tmpSteps = eSteps.getText().toString();
        steps.add(tmpSteps);
        eSteps.getText().clear();

    }

    public void submitRecipe(View view){
        EditText eName = findViewById(R.id.editName);
        String sName = eName.getText().toString();
        completedRecipe.setName(sName);
        completedRecipe.setSteps(steps);
        String id = mDatabase.push().getKey();

        mDatabase.child(id).setValue(completedRecipe, new DatabaseReference.CompletionListener(){
            public void onComplete(DatabaseError error, DatabaseReference ref){
                System.out.println("Value was set. Error = "+error);
                Toast.makeText(createRecipeActivity.this,"word",Toast.LENGTH_SHORT);
                }
        });
    }
}
