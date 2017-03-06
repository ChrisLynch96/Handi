package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.handi.handi.R;

public class SignUpChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_choice);

        overridePendingTransition(0,0);
    }
    public void onClick(View v){
        if (v.getId() == R.id.user_button)
            startActivity(new Intent(this, SignupActivity.class));
        else
            startActivity(new Intent(this, HandiManSignupActivity.class));
    }
}
