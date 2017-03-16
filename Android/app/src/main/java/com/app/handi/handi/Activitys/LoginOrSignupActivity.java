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

import com.app.handi.handi.Firebase.HelperHandiMan;
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
    HelperHandiMan helper;
    boolean isHandiMan = false;
    ArrayList<String> id = new ArrayList<>();
    private ProgressBar progressBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        db = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (user != null) {
            db.child("HandiMen").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.d("cool", "cool");
                    id.clear();
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot child : children) {
                        String Id = child.getKey();
                        id.add(Id);
                        Log.d("String", Id);
                    }
                    for (int i = 0; i < id.size(); i++) {
                        Log.d("Stuff", id.get(i));
                        if(user.getUid().equals(id.get(i)))
                            isHandiMan=true;
                    }
//                    if (id.size() > 0)
//                        isHandiMan = true;
                    String siz = Integer.toString(id.size());
                    Log.d("size", siz);
                    if (!isHandiMan) {
                        startActivity(new Intent(LoginOrSignupActivity.this, UserHomeActivity.class));
                    }
                    else {
                        startActivity(new Intent(LoginOrSignupActivity.this, HandiHomeActivity.class));
                    }
                    finish();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else {
            setContentView(R.layout.activity_login_signup);
            overridePendingTransition(0,0);
            View container = findViewById(R.id.buttons_container);
            container.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        }

    }
    public void onClick(View v){
        if (v.getId() == R.id.login_button)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, SignUpChoiceActivity.class));
    }
}
