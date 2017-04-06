package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 27/02/2017.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.handi.handi.Adapters.DisplayingHandiAdapter;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
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
    ImageView handiImage, handiBackground;
    String profession;
    Job job;
    boolean moreQuotes;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selection);
        overridePendingTransition(0,0);
        listView = (ListView) findViewById(R.id.activity_job_selection_ListView_handi_display);
        db = FirebaseDatabase.getInstance().getReference();
        job = (Job)getIntent().getSerializableExtra("Job");
        Bundle bundle = getIntent().getExtras();
        profession = bundle.getString("profession");
        moreQuotes = bundle.getBoolean("moreQuotes");
        changeBackgroundColour();
        Log.d("prof2",profession);
        helper = new HelperHandiMan(db);
        //Retrieving the Handi from the Database.
        adapter = new DisplayingHandiAdapter(helper.retrieve(profession), this);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Display The Handi with the specific profession.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HandimanData h = (HandimanData) view.getTag();
                if(h==null)
                    Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(JobSelectionActivity.this, HandiProfileView.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Job",job);
                    intent.putExtras(bundle);
                    intent.putExtra("moreQuotes",moreQuotes);
                    intent.putExtra("HandiName", h.getName());
                    intent.putExtra("HandiPhone",h.getNumber());
                    intent.putExtra("HandiEmail",h.getEmail());
                    intent.putExtra("HandiUid",h.getUid());
                    intent.putExtra("HandiProf",h.getProfession());
                    intent.putExtra("Rating",h.getRating());
                    startActivity(intent);
                }
            }
        });

        //setJobImageHeights(images);
    }
    //The Displays On the top of the screen to change profession.
    public void onClick(View view) {
        if (view.getId() == R.id.activity_job_selection_LinearLayout_click_cleaner) {
            profession = "Cleaner";
            Log.d("works",profession);
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        } else if (view.getId() == R.id.activity_job_selection_LinearLayout_click_electrician) {
            profession = "Electrician";
            Log.d("works",profession);
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        } else if (view.getId() == R.id.activity_job_selection_LinearLayout_click_painter) {
            profession = "Painter";
            Log.d("works",profession);
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        } else if (view.getId() == R.id.activity_job_selection_LinearLayout_click_plumber) {
            profession = "Plumber";
            Log.d("works",profession);
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        } else if (view.getId() == R.id.activity_job_selection_LinearLayout_click_handiman) {
            profession = "HandiMan";
            Log.d("works",profession);
            intent = new Intent(this,JobSelectionActivity.class);
            intent.putExtra("profession",profession);
            startActivity(intent);
        }
    }
    //Highlight whatever button was pressed
    public void changeBackgroundColour(){
        switch (profession){
            case "Cleaner":
                handiBackground = (ImageView) findViewById(R.id.activity_job_selection_image_box_cleaner);
                handiImage = (ImageView) findViewById(R.id.activity_job_selection_image_view_cleaner);
                break;
            case "Electrician":
                handiBackground = (ImageView) findViewById(R.id.activity_job_selection_image_box_electrician);
                handiImage = (ImageView) findViewById(R.id.activity_job_selection_image_view_electrician);
                break;
            case "Plumber":
                handiBackground = (ImageView) findViewById(R.id.activity_job_selection_image_box_plumber);
                handiImage = (ImageView) findViewById(R.id.activity_job_selection_image_view_plumber);
                break;
            case "Painter":
                handiBackground = (ImageView) findViewById(R.id.activity_job_selection_image_box_painter);
                handiImage = (ImageView) findViewById(R.id.activity_job_selection_image_view_painter);
                break;
            case "HandiMan":
                handiBackground = (ImageView) findViewById(R.id.activity_job_selection_image_box_handiman);
                handiImage = (ImageView) findViewById(R.id.activity_job_selection_image_view_handiman);
                break;
        }
        handiImage.setColorFilter(ContextCompat.getColor(this,R.color.white));
        handiBackground.setVisibility(View.VISIBLE);
    }
}
