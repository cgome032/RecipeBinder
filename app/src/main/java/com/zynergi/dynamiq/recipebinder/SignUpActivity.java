package com.zynergi.dynamiq.recipebinder;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {


    private static final String TAG = "EmailPassword";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btn = (Button) findViewById(R.id.btn_signUp);
  //      btn.setOnClickListener(new View.OnClickListener(){
    //        public void onClick(View view){

        mAuth = FirebaseAuth.getInstance();



                }
        public void signUpUser(View view){
            EditText textemail = findViewById(R.id.textEmail);
            EditText textpass = findViewById(R.id.textPass);
            String email = textemail.getText().toString();
            String pass = textpass.getText().toString();

            mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG,"createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(SignUpActivity.this,"stored in database",Toast.LENGTH_SHORT)
                        .show();
            } else {
                    Log.w(TAG, "createUserwithEmail:failed",task.getException());
                    Toast.makeText(SignUpActivity.this, "failed to create", Toast.LENGTH_SHORT)
                    .show();
                }

            }
            });
            }
    }


