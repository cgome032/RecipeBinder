package com.zynergi.dynamiq.recipebinder;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zynergi.dynamiq.recipebinder.Post.Recipe;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class createRecipeActivity extends Fragment {

    private static final String TAG ="firestore";
    Recipe completedRecipe = new Recipe();
    ArrayList<String> steps;
    ArrayList<String> ingredients;
//    Map<String, Object> data1 = new HashMap<>();
    Recipe recipeObject = new Recipe();
 //   DatabaseReference mDatabase;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    CollectionReference recipes = db.collection("recipes");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_create_recipe, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
   //     mDatabase = FirebaseDatabase.getInstance().getReference("recipes");
        steps = new ArrayList<>();
        ingredients = new ArrayList<>();

    }

    public void addIngredients(View view){
        EditText eIngredients = (EditText) getView().findViewById(R.id.editIngredients);
        String tmpIngredients = eIngredients.getText().toString();
        ingredients.add(tmpIngredients);
     //   completedRecipe.addIngredient(tmpIngredients);
        recipeObject.addIngredient(tmpIngredients);
        eIngredients.getText().clear();

    }

    public void addSteps(View view){
        EditText eSteps = getView().findViewById(R.id.editSteps);
        String tmpSteps = eSteps.getText().toString();
        steps.add(tmpSteps);
        eSteps.getText().clear();

    }

    public void submitRecipe(View view){
        EditText eName = getView().findViewById(R.id.editName);
        String sName = eName.getText().toString();
    //    completedRecipe.setName(sName);
    //    completedRecipe.setSteps(steps);
        recipeObject.setName(sName);
        recipeObject.setSteps(steps);
        db.collection("recipes")
                .add(recipeObject)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        steps.clear();
        ingredients.clear();

    }
/*
        mDatabase.child(id).setValue(completedRecipe, new DatabaseReference.CompletionListener(){
            public void onComplete(DatabaseError error, DatabaseReference ref){
                System.out.println("Value was set. Error = "+error);
                Toast.makeText(createRecipeActivity.this,"word",Toast.LENGTH_SHORT);
                }
        });
    }
    */
}
