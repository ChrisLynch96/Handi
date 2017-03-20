package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.app.handi.handi.R;

/**
 * Created by christopherlynch on 02/03/2017.
 */

//Todo Comment your code!!!

public class JobDescriptionActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);

    }

    public void onClick(View v){
        startActivity(new Intent(this, JobSelectionActivity.class));
    }
}
