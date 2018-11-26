/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zynergi.dynamiq.recipebinder.RecipeFeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.zynergi.dynamiq.recipebinder.Adapter.RecipeFeedAdapter;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.Post.Recipe;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.ArrayList;
import java.util.List;

public class FeedViewFragment extends Fragment {

    private FirebaseFirestore mFirebaseFirestore = FirebaseFirestore.getInstance();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Post> mDataset;
    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private static View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        /*
        Recipe friedchicken = new Recipe();
        friedchicken.setName("fried chicken");
        mDataset.add(new Post(friedchicken));

        Recipe Soup = new Recipe();
        Soup.setName("Soup");
        mDataset.add(new Post(Soup));

        Recipe padthai = new Recipe();
        padthai.setName("pad thai");
        mDataset.add(new Post(padthai));

        Recipe tokyoTea = new Recipe();
        tokyoTea.setName("tokyo tea");
        mDataset.add(new Post(tokyoTea));

        Recipe pasta = new Recipe();
        pasta.setName("pasta");
        mDataset.add(new Post(pasta));

        Recipe shrimpTaco = new Recipe();
        shrimpTaco.setName("shrimp taco");
        mDataset.add(new Post(shrimpTaco));

        Recipe Taco = new Recipe();
        Taco.setName("taco");
        mDataset.add(new Post(Taco));
        /*for (int i = 0; i < 10; i++) {
            String id = "String" + i;
            Post newAdd = new Post(id);
            mDataset.add(newAdd);
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataset = new ArrayList<>();
        rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        initDataset(new FeedCallBack() {
            @Override
            public void onCallback(List<Recipe> recipes) {
                mRecipes.addAll(recipes);
                for(Recipe recipe: mRecipes) {
                    Log.d("Recipe name: " , recipe.getName());
                    mDataset.add(new Post(recipe));
                }
                Log.d("OC", "Recipes done");
                mRecyclerView = rootView.findViewById(R.id.recyclerView);
                mAdapter = new RecipeFeedAdapter(getContext(), mDataset);
                mRecyclerView.setAdapter(mAdapter);

                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                Log.d("OCV", "OCV");
            }
        });



        //mRecyclerView.setHasFixedSize(true);

        //((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);



        // Set CustomAdapter as the adapter for RecyclerView.


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     * */

    public interface FeedCallBack {
        void onCallback(List<Recipe> recipes);
    }

    private void initDataset(final FeedCallBack feedCallBack) {
        CollectionReference mRecipes =mFirebaseFirestore.collection("recipes");
        mRecipes.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Recipe> recipes = queryDocumentSnapshots.toObjects(Recipe.class);
                feedCallBack.onCallback(recipes);
            }
        });
    }

}