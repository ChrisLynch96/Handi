package com.app.handi.handi.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.User;
import com.app.handi.handi.Firebase.HelperUser;
import com.app.handi.handi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserSignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputFirstName, inputLastName;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private DatabaseReference ref;
    private User userdb;
    boolean saved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        overridePendingTransition(0, 0);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputFirstName = (EditText) findViewById(R.id.firstName);
        inputLastName = (EditText) findViewById(R.id.lastName);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserSignupActivity.this, LoginActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String firstName = inputFirstName.getText().toString().trim();
                final String lastName = inputLastName.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(firstName)) {
                    Toast.makeText(getApplicationContext(), "Enter first name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(lastName)) {
                    Toast.makeText(getApplicationContext(), "Enter last name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(UserSignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(UserSignupActivity.this, "Account creation: " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(UserSignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(UserSignupActivity.this, UserHomeActivity.class));
                                    user = FirebaseAuth.getInstance().getCurrentUser();
                                    assert user != null;
                                    userdb = new User(firstName,lastName,email,user.getUid());
                                    ref = FirebaseDatabase.getInstance().getReference();
                                    HelperUser db = new HelperUser(ref);
                                    saved = db.saveInfo(userdb,user);
//                                    if(saved){
//                                        Toast.makeText(UserSignupActivity.this,"Data Saved."+task.getException(),Toast.LENGTH_SHORT).show();
//                                    }
//                                    else{
//                                        Toast.makeText(UserSignupActivity.this,"Not Saved."+task.getException(),Toast.LENGTH_SHORT).show();
//                                    }
                                    finish();
                                }
                            }
                        });

            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        progressBar.setVisibility(View.GONE);
//    }
}