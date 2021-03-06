package com.zynergi.dynamiq.recipebinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zynergi.dynamiq.recipebinder.Adapter.createRecipeAdapter;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.Post.Recipe;
import com.zynergi.dynamiq.recipebinder.Profile.Profile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String uid;
    private String sName;
    private List<String> recipes;
    private EditText eSteps;
    private EditText eIngredients;
    private EditText eName;
    private String postUid;
    private String recipe;
    private List<String> postUIDs;
    private String recipeId;
    private RecyclerView IngredientView;
    private createRecipeAdapter IngredientAdapter;
    private RecyclerView.LayoutManager layoutManager1;
    private Context context;
    private RecyclerView StepsView;
    private createRecipeAdapter StepsAdapter;
    private RecyclerView.LayoutManager layoutManager2;
    TextView commentsView;

//    CollectionReference recipes = db.collection("recipes");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_create_recipe, container, false);

        IngredientView = rootView.findViewById(R.id.IngredientsRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        IngredientView.setHasFixedSize(true);
        IngredientView.setLayoutManager(layoutManager);
        IngredientAdapter = new createRecipeAdapter(context, ingredients);
        IngredientView.setAdapter(IngredientAdapter);

        StepsView = rootView.findViewById(R.id.StepsRecycler);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(context);
        StepsView.setHasFixedSize(true);
        StepsView.setLayoutManager(layoutManager2);
        StepsAdapter = new createRecipeAdapter(context, steps);
        StepsView.setAdapter(IngredientAdapter);



        // Add Ingredients method
        Button addIngredientButton = rootView.findViewById(R.id.btnIngred);
        eIngredients = rootView.findViewById(R.id.editIngredients);
        eSteps = rootView.findViewById(R.id.editSteps);

        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //isEmpty = true;
                if (!eIngredients.getText().toString().isEmpty())
                    addIngredients(v);
            }
        });

        // Add steps method
        Button addStepsButton = rootView.findViewById(R.id.btnSteps);
        addStepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eSteps.getText().toString().isEmpty())
                    addSteps(v);
            }
        });


        // Submit Recipe
        Button submitRecipeButton = rootView.findViewById(R.id.btnRecipe);
        submitRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRecipe(v);
            }
        });

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
   //     mDatabase = FirebaseDatabase.getInstance().getReference("recipes");
        steps = new ArrayList<>();
        ingredients = new ArrayList<>();
        recipes = new ArrayList<>();
        postUIDs = new ArrayList<>();

    }

    public void addIngredients(View view){
        String tmpIngredients = eIngredients.getText().toString();
        ingredients.add(tmpIngredients);
     //   completedRecipe.addIngredient(tmpIngredients);
        eIngredients.getText().clear();
        createRecipeAdapter mAdapter = new createRecipeAdapter(context, ingredients);
        //mRecyclerView = view.findViewById(R.id.recyclerView);
        IngredientView.setHasFixedSize(true);
        IngredientView.setLayoutManager(new LinearLayoutManager(getActivity()));
        IngredientView.setAdapter(mAdapter);

    }

    public void addSteps(View view){
        String tmpSteps = eSteps.getText().toString();
        steps.add(tmpSteps);
        eSteps.getText().clear();
        createRecipeAdapter mAdapter = new createRecipeAdapter(context, steps);
        //mRecyclerView = view.findViewById(R.id.recyclerView);
        StepsView.setHasFixedSize(true);
        StepsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StepsView.setAdapter(mAdapter);
    }

    public void submitRecipe(final View view){
        eName = getView().findViewById(R.id.editName);
        sName = eName.getText().toString();
        if (!sName.isEmpty()) {
            //    completedRecipe.setName(sName);
            //    completedRecipe.setSteps(steps);
            recipeObject.setName(sName);
            recipeObject.setSteps(steps);
            recipeObject.setIngredients(ingredients);
            db.collection("recipes")
                    .add(recipeObject)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(final DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                            Post post = new Post(recipeObject, documentReference.getId());


                            db.collection("posts").add(post).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference1) {
                                    Log.d(TAG, "Post added with id " + documentReference1.getId());
                                    db.collection("posts").document(documentReference1.getId()).update("id", documentReference1.getId());
                                    db.collection("recipes").document(documentReference.getId()).update("postID", documentReference1.getId());
                                    uid = mAuth.getCurrentUser().getUid();
                                    postUid = documentReference1.getId();
                                    DocumentReference docRef = db.collection("profiles").document(uid);
                                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot document = task.getResult();
                                                if (document.exists()) {
                                                    postUIDs = (List<String>) document.getData().get("recipeNames");
                                                    DocumentReference docRef = db.collection("profiles").document(uid);
                                                    postUIDs.add(postUid);
                                                    docRef.update("recipeNames", recipes)
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    Log.d(TAG, "Success to update User");
                                                                }
                                                            });

                                                }
                                            }
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                            ClearAll(view);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }

    }
    public void ClearAll(View view){
        eName = getView().findViewById(R.id.editName);
        steps.clear();
        ingredients.clear();
        eName.getText().clear();
        Log.d(TAG, "Cleared lists");

        createRecipeAdapter mAdapter = new createRecipeAdapter(context, steps);
        //mRecyclerView = view.findViewById(R.id.recyclerView);
        IngredientView.setHasFixedSize(true);
        IngredientView.setLayoutManager(new LinearLayoutManager(getActivity()));
        IngredientView.setAdapter(mAdapter);

        createRecipeAdapter mAdapter2 = new createRecipeAdapter(context, steps);
        //mRecyclerView = view.findViewById(R.id.recyclerView);
        StepsView.setHasFixedSize(true);
        StepsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StepsView.setAdapter(mAdapter2);
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
