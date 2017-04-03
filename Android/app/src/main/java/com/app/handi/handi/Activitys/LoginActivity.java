package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.Firebase.HelperUser;
import com.app.handi.handi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    FirebaseUser user;
    private ProgressBar progressBar;
    boolean isHandiMan = false;
    ArrayList<String> id = new ArrayList<>();
    DatabaseReference db;
    HelperUser helperUser;
    HelperHandiMan helperHandiMan;
    HandimanData handimanData;
    ArrayList<Job> job = new ArrayList<>();
    ArrayList<Job> Hjobs = new ArrayList<>();


    //Todo Check if the login is a handi or user so the system knows which screen to go to
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ignore the default transition
        overridePendingTransition(0,0);
        View container = findViewById(R.id.activity_login_relative_layout_text_button_container);
        //When the activity starts fade in to complement the transition from the previous splash screen
        container.setAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();
    }

    public void onClick(View v){
        // set the view now
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (v.getId() == R.id.btn_login) {
            String email = inputEmail.getText().toString();
            final String password = inputPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                // there was an error
                                progressBar.setVisibility(View.GONE);
                                if (password.length() < 6) {
                                    inputPassword.setError(getString(R.string.minimum_password));
                                } else {
                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                user = auth.getCurrentUser();
                                helperUser = new HelperUser(db);
                                job = helperUser.retrieve(user);
                                helperHandiMan = new HelperHandiMan(db);
                                Hjobs = helperHandiMan.retrieveJob(user);
                                db.child("HandiMen").child(user.getUid()).child("Info").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        handimanData = dataSnapshot.getValue(HandimanData.class);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                db.child("HandiMen").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Log.d("cool", "cool");
                                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                        for (DataSnapshot child : children) {
                                            String Id = child.getKey();
                                            if(Id.equals(user.getUid()))
                                                isHandiMan=true;
                                            Log.d("String", Id);
                                        }
                                        progressBar.setVisibility(View.GONE);
                                        if (!isHandiMan) {
                                            Intent intent = new Intent(LoginActivity.this,UserHomeActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("Jobs",job);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                        else {
                                            Intent intent = new Intent(LoginActivity.this,HandiHomeActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("Jobs",Hjobs);
                                            bundle.putSerializable("Handi",handimanData);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        }
                    });
        }
        else{
            startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
        }
    }
    //Todo Login with google or facebook
}
