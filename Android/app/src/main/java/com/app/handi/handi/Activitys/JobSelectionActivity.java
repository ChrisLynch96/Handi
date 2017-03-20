package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 27/02/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.handi.handi.Adapters.DisplayingHandiAdapter;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    String profession;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selection);
        listView = (ListView) findViewById(R.id.activity_job_selection_ListView_handi_display);

        db = FirebaseDatabase.getInstance().getReference();
//        helper = new HelperHandiMan(db);
//        adapter = new DisplayingHandiAdapter(helper.retrieve(profession),this);
//        listView.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        profession = bundle.getString("profession");
        Log.d("prof2",profession);
        displayList();
    }

    public void displayList() {
        helper = new HelperHandiMan(db);
        adapter = new DisplayingHandiAdapter(helper.retrieve(profession), this);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(JobSelectionActivity.this,"popo",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView[] images = {(ImageView) findViewById(R.id.activity_job_selection_image_view_cleaner), (ImageView) findViewById(R.id.activity_job_selection_image_view_electrician), (ImageView) findViewById(R.id.activity_job_selection_image_view_handiman),
                (ImageView) findViewById(R.id.activity_job_selection_image_view_painter), (ImageView) findViewById(R.id.activity_job_selection_image_view_plumber)};

        setJobImageHeights(images);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.activity_job_selection_image_view_cleaner) {
            profession = "Cleaner";
        } else if (view.getId() == R.id.activity_job_selection_image_view_electrician) {
            profession = "Electrician";
        } else if (view.getId() == R.id.activity_job_selection_image_view_painter) {
            profession = "Painter";
        } else if (view.getId() == R.id.activity_job_selection_image_view_plumber) {
            profession = "Plumber";
        } else if (view.getId() == R.id.activity_job_selection_image_view_handiman) {
            profession = "HandiMan";
        }
    }

    public void setJobImageHeights(ImageView[] jobImageViews){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/5;
        for (int i = 0; i < jobImageViews.length; i++)
            jobImageViews[i].getLayoutParams().height = width;
    }
}
