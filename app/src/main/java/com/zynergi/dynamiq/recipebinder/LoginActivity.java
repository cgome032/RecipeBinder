package com.zynergi.dynamiq.recipebinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onStart(){
        super.onStart();
        FirebaseUser currUser = mAuth.getCurrentUser();
    }

    public void loginClick(View view){
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPass = findViewById(R.id.editPass);
        String email = editEmail.getText().toString();
        String pass = editPass.getText().toString();

        if (!validEmailCheck(email))
        {
            Context context = getApplicationContext();
            CharSequence text = "Invalid email";
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

        }

        if (emptyFieldCheck(pass)){
            Context context = getApplicationContext();
            Toast.makeText(context,"Please enter password", Toast.LENGTH_SHORT);
        }

        if (validEmailCheck(email))
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Failed to Authenticate", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    boolean validEmailCheck (CharSequence email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    } // validEmailCheck

    boolean emptyFieldCheck (CharSequence entry){
        if (entry == "")
            return true;
        else
            return false;

    }
}
