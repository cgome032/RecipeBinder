package com.zynergi.dynamiq.recipebinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zynergi.dynamiq.recipebinder.Recipe_Activity;

public class myRecipeCardView extends AppCompatActivity {

    private TextView myRecipe;
    private Intent intent;
    public static final String EXTRA_STRING = "PassedValue";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_card_view);
        myRecipe = findViewById(R.id.recipeName);
        intent = new Intent(this, Recipe_Activity.class);
        myRecipe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String recipe = myRecipe.getText().toString();
                intent.putExtra(EXTRA_STRING,recipe);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),EXTRA_STRING, Toast.LENGTH_SHORT);
            }
        });
    }


}
