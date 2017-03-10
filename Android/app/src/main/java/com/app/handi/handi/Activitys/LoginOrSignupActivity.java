package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 21/02/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;


public class LoginOrSignupActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginOrSignupActivity.this, UserHomeActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login_signup);

        overridePendingTransition(0,0);
        View container = findViewById(R.id.buttons_container);
        container.setAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
    }

    public void onClick(View v){
        if (v.getId() == R.id.login_button)
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, SignUpChoiceActivity.class));
    }
}
