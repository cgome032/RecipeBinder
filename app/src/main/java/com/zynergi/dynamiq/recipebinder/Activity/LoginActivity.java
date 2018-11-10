package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zynergi.dynamiq.recipebinder.Menu_activity;
import com.zynergi.dynamiq.recipebinder.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button btn_google = (Button) findViewById(R.id.btn_google);
     //   btn_google.setOnClickListener(googleClick(getApplicationContext()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
/*
        // Google Sign-in options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
*/

    }

    public void onStart(){
        super.onStart();
        FirebaseUser currUser = mAuth.getCurrentUser();

    }
   public void loginClick(View view){
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPass = findViewById(R.id.textPass);
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
                            startActivity(new Intent(LoginActivity.this, Menu_activity.class));
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Failed to Authenticate", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
/*
    public void googleSigninClick(View view) {
        /*
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editPass = findViewById(R.id.editPass);
        String email = editEmail.getText().toString();
        String pass = editPass.getText().toString();



        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
  */

    //SignupActivity.class is a placeholder
    // it will be replaced when we merge branches
    public void signupClick(View view){
        Intent myIntent = new Intent(this, SignUpActivity.class);
        startActivity(myIntent);
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
