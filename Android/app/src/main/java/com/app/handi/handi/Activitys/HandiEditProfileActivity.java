package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.handi.handi.R;

/**
 * Created by christopherlynch on 19/03/2017.
 */

public class HandiEditProfileActivity extends AppCompatActivity {

    public void onCreate(Bundle savedBundleInstance){
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_handi_edit_profile);
    }

    public void onClick(View view){
        startActivity(new Intent(this, HandiHomeActivity.class));
    }
}
