package com.zynergi.dynamiq.recipebinder;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.zynergi.dynamiq.recipebinder.Activity.LoginActivity;
import com.zynergi.dynamiq.recipebinder.Adapter.recipesAdapter;
import com.zynergi.dynamiq.recipebinder.Post.MyAdapter;
import com.zynergi.dynamiq.recipebinder.Profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends Fragment {

    private static TextView txtProfileName;
    private static EditText txtDescription;
    private String id;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    private Profile mUser;
    private static final String TAG = "found user at FS";
    private Button btnDescr;
    private Button btnSignUp;
    /*
    private RecyclerView mRecyclerViewRecipes;
    private recipesAdapter AdapterRecipes;
    private RecyclerView.LayoutManager mLayoutManagerRecipes;
    private List<String> recipeList;
    */
    private Context mContext;

    View rootView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mContext = getActivity().getApplicationContext();

        id = user.getUid();

        if (user != null){
            // SIGNED IN
            Toast.makeText(mContext,"filled user",Toast.LENGTH_SHORT);
        }
        else{
            // NOT SIGNED IN
            Toast.makeText(mContext,"Empty user",Toast.LENGTH_SHORT);
        }

        DocumentReference docRef = db.collection("profiles").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name = document.getData().get("name").toString();
                        String description = document.getData().get("description").toString();

                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        txtProfileName.setText(name);
                        txtDescription.setText(description);
                        /*
                        recipeList = (List<String>) document.getData().get("recipeNames");
                        mRecyclerViewRecipes = rootView.findViewById(R.id.recipeList);
                        mLayoutManagerRecipes = new LinearLayoutManager(getActivity());
                        mRecyclerViewRecipes.setHasFixedSize(true);
                        mRecyclerViewRecipes.setLayoutManager(mLayoutManagerRecipes);
                        AdapterRecipes = new recipesAdapter(recipeList, mContext);
                        mRecyclerViewRecipes.setAdapter(AdapterRecipes);
                           */
                    }
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.activity_profile, container, false);
        else
            return rootView;
        txtProfileName = rootView.findViewById(R.id.nameTxt);
        txtDescription = rootView.findViewById(R.id.descriptionTxt);
        btnDescr = rootView.findViewById(R.id.descrBtn);
        btnDescr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDescription(v);
            }
        });

        btnSignUp = rootView.findViewById(R.id.signout);
        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SignOut(v);
            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        return rootView;

    }

    public void editDescription (View view){
        String nDescription;
        nDescription = txtDescription.getText().toString();
        DocumentReference docRef = db.collection("profiles").document(id);
        docRef.update("description", nDescription)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Snapshot successfully updated");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document",e);
                    }
                });

    }

    public void SignOut(View view){
        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
    }


/*
    public void onViewCreated(View view, Bundle saveInstanceState){
        txtProfileName.setText("HI");
        txtDescription.setText("WORLD");

    }
    */
}
