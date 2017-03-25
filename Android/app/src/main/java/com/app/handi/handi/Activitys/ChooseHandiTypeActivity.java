package com.app.handi.handi.Activitys;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.handi.handi.R;

import java.util.Locale;

public class ChooseHandiTypeActivity extends AppCompatActivity {
    String profession;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_handi);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/LT.ttf");
        // text view label
        TextView cleaner = (TextView) findViewById(R.id.activity_job_selection_image_text_cleaner);
        TextView electrician = (TextView) findViewById(R.id.activity_job_selection_image_text_electrician);
        TextView painter = (TextView) findViewById(R.id.activity_job_selection_image_text_painter);
        TextView plumber = (TextView) findViewById(R.id.activity_job_selection_image_text_plumber);
        TextView handiman = (TextView) findViewById(R.id.activity_job_selection_image_text_handiman);
        // Applying font
        cleaner.setTypeface(tf);
        electrician.setTypeface(tf);
        painter.setTypeface(tf);
        plumber.setTypeface(tf);
        handiman.setTypeface(tf);
    }

    public void onClick(View view) {
        if(view.getId()==R.id.activity_job_selection_relative_layout_cleaner){
            profession = "Cleaner";
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            Log.d("prof",profession);
            startActivity(intent);
        }
        else if(view.getId()==R.id.activity_job_selection_relative_layout_electrician){
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
        else if(view.getId()==R.id.activity_job_selection_relative_layout_painter){
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
