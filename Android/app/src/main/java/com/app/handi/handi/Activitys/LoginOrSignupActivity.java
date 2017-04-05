package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 21/02/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.Firebase.HelperUser;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LoginOrSignupActivity extends AppCompatActivity {
    DatabaseReference db;
    FirebaseUser user;
    boolean isHandiMan = false;
    ArrayList<String> id = new ArrayList<>();
    HelperUser helperUser;
    HelperHandiMan helperHandiMan;
    ArrayList<Job> job = new ArrayList<>();
    ArrayList<Job> Hjobs = new ArrayList<>();
    HandimanData handimanData;



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        db = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.activity_login_signup_progress_bar_pBar);
        overridePendingTransition(0,0);
        View container = findViewById(R.id.buttons_container);
        container.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        //if User is already logged in checks if user is Handi or User.
        if (user != null) {
            progressBar.setVisibility(View.VISIBLE);
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
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot child : children) {
                        String Id = child.getKey();
                        if(Id.equals(user.getUid())) {
                            isHandiMan = true;
                        }
                        Log.d("String", Id);
                    }
                    if (!isHandiMan) {
                        Intent intent = new Intent(LoginOrSignupActivity.this,UserHomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Jobs",job);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(LoginOrSignupActivity.this,HandiHomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Jobs",Hjobs);
                        bundle.putSerializable("Handi",handimanData);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
    public void onClick(View v){
        if (v.getId() == R.id.login_button)
            startActivity(new Intent(LoginOrSignupActivity.this, LoginActivity.class));
        else
            startActivity(new Intent(LoginOrSignupActivity.this, SignUpChoiceActivity.class));
    }
}
