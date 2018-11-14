package com.zynergi.dynamiq.recipebinder.Activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.zynergi.dynamiq.recipebinder.Profile.Profile;
import com.zynergi.dynamiq.recipebinder.R;

public class SignUpActivity extends AppCompatActivity {


    private static final String TAG = "EmailPassword";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private EditText textemail;
    private EditText textpass;
    private EditText textName;
    private EditText textDescription;
    private Button btn;
    private Profile profileObj;
    private FirebaseUser user;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textemail = findViewById(R.id.textEmail);
        textpass = findViewById(R.id.textPass);
        textName = findViewById(R.id.txtName);
        textDescription = findViewById(R.id.txtDesc);
        textDescription.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        profileObj = new Profile();

        btn = (Button) findViewById(R.id.btn_signUp);
  //      btn.setOnClickListener(new View.OnClickListener(){
    //        public void onClick(View view){

        mAuth = FirebaseAuth.getInstance();


                }
        public void signUpUser(View view) {
            String email = textemail.getText().toString();
            String pass = textpass.getText().toString();
            String sName = textName.getText().toString();
            String sDescription = textDescription.getText().toString();
            profileObj.setName(sName);
            profileObj.setDescription(sDescription);

            if (!validEmailCheck(email)) {
                Context context = getApplicationContext();
                CharSequence text = "Invalid email";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

            }

            if (validEmailCheck(email)) {
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    user = mAuth.getCurrentUser();
                                    String id = user.getUid();
                                    Toast.makeText(getApplicationContext(), "ici", Toast.LENGTH_SHORT).show();
                                    db.collection("profiles").document(id)
                                            .set(profileObj)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error writing Document");
                                                }
                                            });

                                    Toast.makeText(SignUpActivity.this, "stored in database", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    Log.w(TAG, "createUserwithEmail:failed", task.getException());
                                    Toast.makeText(SignUpActivity.this, "failed to create", Toast.LENGTH_SHORT)
                                            .show();
                                    //                                   return;

                                }

                            }
                        });
                ;
            }
//            user = mAuth.getCurrentUser(
        /*
            if (user != null) {
                String id = user.getUid();
                Toast.makeText(getApplicationContext(), "ici", Toast.LENGTH_SHORT).show();
                db.collection("profiles").document(id)
                        .set(profileObj)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing Document");
                            }
                        });

            }
            */
        }
    boolean validEmailCheck (CharSequence email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    } // validEmailCheck

}


