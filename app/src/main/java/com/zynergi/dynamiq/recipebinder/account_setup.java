package com.zynergi.dynamiq.recipebinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zynergi.dynamiq.recipebinder.Profile.Profile;

public class account_setup extends AppCompatActivity {

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void createProfile(View view)
    {
        EditText textfullname = findViewById(R.id.username);
        EditText textdescription = findViewById(R.id.description);
        EditText textusername = findViewById(R.id.username);

        String sname = textfullname.getText().toString();
        String sdescrip = textdescription.getText().toString();
        String susername = textusername.getText().toString();
        writeNewUser(susername,sname, sdescrip);

    }

    private void writeNewUser(String userId, String name, String descrip){
        Profile user = new Profile(name,descrip);
        mDatabase.child("users").child(userId).setValue(user);
        Toast.makeText(account_setup.this,"were here",Toast.LENGTH_SHORT).show();

    }
}
