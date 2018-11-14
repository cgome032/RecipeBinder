package com.zynergi.dynamiq.recipebinder;

import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.zynergi.dynamiq.recipebinder.Post.MyAdapter;
import com.zynergi.dynamiq.recipebinder.Profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends Fragment {

    private EditText txtProfileName;
    private EditText txtDescription;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Profile mUser;
    private static final String TAG = "found user at FS";

    private RecyclerView mRecyclerViewRecipes;
    private MyAdapter mAdapterRecipes;
    private RecyclerView.LayoutManager mLayoutManagerRecipes;
    private Context mContext;
    private List<String> recipeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_create_recipe, container, false);
        txtProfileName = rootView.findViewById(R.id.txtName);
        txtDescription = rootView.findViewById(R.id.txtDesc);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mContext = getActivity().getApplicationContext();
        recipeList = new ArrayList<>();

        txtProfileName.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        txtDescription.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        String id;
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
                                                   if(task.isSuccessful()){
                                                       DocumentSnapshot document = task.getResult();
                                                       Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                                       txtProfileName.setText(document.get("name").toString());
                                                       txtDescription.setText(document.get("description").toString());
                                                       recipeList = (List<String>) document.get("recipeNames");

                                                       mRecyclerViewRecipes = rootView.findViewById(R.id.recipeList);
                                                       mLayoutManagerRecipes = new LinearLayoutManager(getActivity());
                                                       mRecyclerViewRecipes.setHasFixedSize(true);
                                                       mRecyclerViewRecipes.setLayoutManager(mLayoutManagerRecipes);
                                                       mAdapterRecipes = new MyAdapter(recipeList, mContext);
                                                       mRecyclerViewRecipes.setAdapter(mAdapterRecipes);

                                                   }
                                                   else{
                                                       Log.d(TAG, "DocumentSnapshot data: " + task.getException());
                                                   }

                                               }
                                           });

        return rootView;


    }
}
