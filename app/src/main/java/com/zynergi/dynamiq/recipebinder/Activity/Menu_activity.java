package com.zynergi.dynamiq.recipebinder;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.BottomNavigationView;
import android.widget.Toast;
import com.zynergi.dynamiq.recipebinder.R;
public class Menu_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setBottomNavigation();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    case R.id.action_create_recipe:
                        fragment = new createRecipeActivity();
                        loadFragment(fragment);
                        Toast.makeText(Menu_activity.this, "Create Recipe", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(Menu_activity.this, "Create Recipe", Toast.LENGTH_SHORT).show();
                        break;
