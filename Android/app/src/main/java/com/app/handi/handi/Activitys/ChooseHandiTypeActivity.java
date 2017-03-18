package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.app.handi.handi.R;

public class ChooseHandiTypeActivity extends AppCompatActivity {
    String profession;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_handi);
    }

    public void onClick(View view) {
        if(view.getId()==R.id.activity_job_selection_LinearLayout_cleaner){
            profession = "Cleaner";
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            Log.d("prof",profession);
            startActivity(intent);
        }
        else if(view.getId()==R.id.activity_job_selection_LinearLayout_electrician){
            profession = "Electrician";
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        }
        else if(view.getId()==R.id.activity_job_selection_LinearLayout_plumber){
            profession = "Plumber";
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        }
        else if(view.getId()==R.id.activity_job_selection_LinearLayout_painter){
            profession = "Painter";
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        }
        else {
            profession = "HandiMan";
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        }
    }
}
