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

package com.zynergi.dynamiq.recipebinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.zynergi.dynamiq.recipebinder.Adapter.PostAdapter;
import com.zynergi.dynamiq.recipebinder.Adapter.RecipeFeedAdapter;
import com.zynergi.dynamiq.recipebinder.Post.Post;
import com.zynergi.dynamiq.recipebinder.Profile.Profile;
import com.zynergi.dynamiq.recipebinder.R;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class FavoritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecipeFeedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth user = FirebaseAuth.getInstance();
    private String uid = user.getCurrentUser().getUid();
    private Context context = this.getContext();
    private List<Post> mDataset;
    private ArrayList<Post> mPosts = new ArrayList<>();
    private static View rootView;


    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("OnCreate", "here");
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataset = new ArrayList<>();
        rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        Log.d("Before onSuccess", "Here");

        initDataset(new FeedCallBack() {
            @Override
            public void onCallBack(List<Post> posts) {
                mPosts.addAll(posts);

                mRecyclerView = rootView.findViewById(R.id.recyclerView);
                mAdapter = new RecipeFeedAdapter(getContext(), posts);
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mAdapter);



            }
        });

        return rootView;
    }

    public interface FeedCallBack {
        void onCallBack(List<Post> posts);
    }

    private void initDataset(final FeedCallBack feedCallBack) {
        DocumentReference mDocument = db.collection("profiles").document(uid);
        mDocument.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Profile profile = documentSnapshot.toObject(Profile.class);

                List<String> favorites = profile.getFavoriteRecipes();
                List<Post> posts = new ArrayList<>();

                String size = new Integer(favorites.size()).toString();

                Log.d("Posts size", size);

                for (int i = 0; i < favorites.size(); i++) {
                    Post post = new Post(favorites.get(i));
                    posts.add(post);
                }
                feedCallBack.onCallBack(posts);

            }
        });
    }

}