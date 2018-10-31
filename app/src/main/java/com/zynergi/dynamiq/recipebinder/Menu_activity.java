package com.zynergi.dynamiq.recipebinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;

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
}
