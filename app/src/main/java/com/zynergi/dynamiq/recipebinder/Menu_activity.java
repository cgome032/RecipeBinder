package com.zynergi.dynamiq.recipebinder;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.support.design.widget.BottomNavigationView;
import android.widget.Toast;

public class Menu_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_activity);
    }

    public void createRecipe(View view) {
        Intent myIntent = new Intent(this, createRecipeActivity.class);
        startActivity(myIntent);
    }

    public void viewRecipe(View view) {
        Intent myIntent = new Intent(this, Recipe_Activity.class);
        startActivity(myIntent);
    }

    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        //bottomNavigationView.setSelectedItemId(R.id.home_screen_fragment);

        bottomNavigationView.getMenu().findItem(R.id.action_home).setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //fragment = new HomeScreenFragment();
                        //loadFragment(fragment);

                        break;

                    case R.id.action_favorites:
                        Toast.makeText(Menu_activity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_create_recipe:
                        Toast.makeText(Menu_activity.this, "Create Recipe", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_account:
                        Toast.makeText(Menu_activity.this, "Account", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }

    /*
    private void loadFragment(Fragment fragment) {
        // Loading fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    */
}
