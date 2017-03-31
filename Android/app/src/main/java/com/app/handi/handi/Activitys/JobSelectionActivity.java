package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 27/02/2017.
 */

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobSelectionActivity extends AppCompatActivity {

    DatabaseReference db;
    HelperHandiMan helper;
    DisplayingHandiAdapter adapter;
    ListView listView;
    ArrayList<HandimanData> data = new ArrayList<>();
    ImageView i;
    String profession;
    Intent intent;
    View currentJobView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selection);
        overridePendingTransition(0,0);
        listView = (ListView) findViewById(R.id.activity_job_selection_ListView_handi_display);

        db = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        profession = bundle.getString("profession");
        Log.d("prof2",profession);
        helper = new HelperHandiMan(db);
        adapter = new DisplayingHandiAdapter(helper.retrieve(profession), this);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HandimanData h = (HandimanData) view.getTag();
                if(h==null)
                    Log.d("m","notok");
                else {
                    Toast.makeText(getBaseContext(), h.getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(JobSelectionActivity.this, HandiProfileView.class);
                    intent.putExtra("HandiName", h.getName());
                    intent.putExtra("HandiPhone",h.getNumber());
                    intent.putExtra("HandiEmail",h.getEmail());
                    intent.putExtra("HandiUid",h.getUid());
                    intent.putExtra("HandiProf",h.getProfession());
                    startActivity(intent);
                }
            }
        });

        //setJobImageHeights(images);
    }

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

//    public void setJobImageHeights(ImageView[] jobImageViews){
//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x/5;
//        for (int i = 0; i < jobImageViews.length; i++)
//            jobImageViews[i].getLayoutParams().height = width;
//    }
}
