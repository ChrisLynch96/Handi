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
    ImageView imageBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_handi);

        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/LT.ttf");
        // Loading TextViews into an arrat
        TextView[] textViews = {(TextView) findViewById(R.id.activity_choose_handi_text_view_header), (TextView) findViewById(R.id.activity_choose_handi_text_view_cleaner),
                (TextView) findViewById(R.id.activity_choose_handi_text_view_plumber), (TextView) findViewById(R.id.activity_choose_handi_text_view_electrician),
                (TextView) findViewById(R.id.activity_choose_handi_text_view_handiman), (TextView) findViewById(R.id.activity_choose_handi_text_view_painter)};
        //setting typeface
        setTypeFace(textViews, tf);
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

    //function that changes the an array of TextViews to a certian typeface
    public void setTypeFace(TextView[] texts, Typeface tf) {
        for (int i = 0; i < texts.length; i++)
            texts[i].setTypeface(tf);
    }
}
