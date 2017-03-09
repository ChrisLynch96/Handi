package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 27/02/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.handi.handi.Adapters.DisplayingHandiAdapter;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobSelectionActivity extends AppCompatActivity {

    DatabaseReference db;
    HelperHandiMan helper;
    DisplayingHandiAdapter adapter;
    ListView listView;
    ArrayList<HandimanData> d = new ArrayList<HandimanData>();
    ImageView i;
    String profession = "Plumber";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selection);
//<<<<<<< Updated upstream
//
//        // Icons
//        ImageView cleanerIcon = (ImageView) findViewById(R.id.activity_job_selection_image_view_cleaner);
//        ImageView electricianIcon = (ImageView) findViewById(R.id.activity_job_selection_image_view_electrician);
//        ImageView painterIcon = (ImageView) findViewById(R.id.activity_job_selection_image_view_painter);
//        ImageView handimanIcon = (ImageView) findViewById(R.id.activity_job_selection_image_view_handiman);
//        ImageView plumberIcon = (ImageView) findViewById(R.id.activity_job_selection_image_view_plumber);
//
//        //Creating an array containing the job images so I can just iterate through them.
//        ImageView[] jobImageViews = {cleanerIcon, electricianIcon, painterIcon, handimanIcon, plumberIcon};
//
//        setJobImageHeights(jobImageViews);
//        DisplayingHandiAdapter dispHandiAdapter = new DisplayingHandiAdapter(this, handiProfilePics, handiNames, handiEmails, handiPhoneNumbers);
//=======
//>>>>>>> Stashed changes
        listView = (ListView) findViewById(R.id.activity_job_selection_ListView_handi_display);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new HelperHandiMan(db);
//        HandimanData man = new HandimanData("bob","lano@tcd.ie","1/1/90","09876666","plumber",i);
//        d.add(man);
        adapter = new DisplayingHandiAdapter(helper.retrieve(profession),this);
        listView.setAdapter(adapter);
    }

    public void onClick(View view){
        if(view.getId()==R.id.activity_job_selection_image_view_cleaner){
            profession = "Cleaner";
        }
        else if(view.getId()==R.id.activity_job_selection_image_view_electrician){
            profession = "Electrician";
        }
        else if(view.getId()==R.id.activity_job_selection_image_view_painter){
            profession = "Painter";
        }
        else if(view.getId()==R.id.activity_job_selection_image_view_plumber){
            profession = "Plumber";
        }
        else if(view.getId()==R.id.activity_job_selection_image_view_handiman){
            profession = "HandiMan";
        }
    }
}
