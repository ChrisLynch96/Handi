package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 27/02/2017.
 */

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.handi.handi.Adapters.DisplayingHandiAdapter;
import com.app.handi.handi.R;
import java.util.ArrayList;

public class JobSelectionActivity extends AppCompatActivity {

    ListView listView;
    Context context;
    ArrayList progList;

    //Creating a temporary array of android images just to test the list view - Chris
    private static Integer[] handiProfilePics = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    //Creating temporary array of strings for testing list view - Chris
    private static String[] handiNames = {
        "David Gregg", "David Gregg", "David Gregg", "David Gregg",
            "David Gregg", "David Gregg", "David Gregg", "David Gregg",
            "David Gregg", "David Gregg", "David Gregg", "David Gregg"
    };

    private static String[] handiEmails = {
            "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg",
            "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg",
            "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg",
            "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg", "davidGregg@davidgreggfanclub.gregg"
    };

    private static String[] handiPhoneNumbers = {
            "0851234567", "0851234567", "0851234567", "0851234567",
            "0851234567", "0851234567", "0851234567", "0851234567",
            "0851234567", "0851234567", "0851234567", "0851234567"
    };



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selection);

        //Creating an array containing the job images so I can just iterate through them.
        ImageView[] jobImageViews = {(ImageView) findViewById(R.id.activity_job_selection_image_view_cleaner), (ImageView) findViewById(R.id.activity_job_selection_image_view_electrician)
                , (ImageView) findViewById(R.id.activity_job_selection_image_view_painter), (ImageView) findViewById(R.id.activity_job_selection_image_view_handiman)
                , (ImageView) findViewById(R.id.activity_job_selection_image_view_plumber)};

        setJobImageHeights(jobImageViews);
        DisplayingHandiAdapter dispHandiAdapter = new DisplayingHandiAdapter(this, handiProfilePics, handiNames, handiEmails, handiPhoneNumbers);
        listView = (ListView) findViewById(R.id.activity_job_selection_ListView_handi_display);
        listView.setAdapter(dispHandiAdapter);
    }

    /*
    This sets the heights of the job images so they appear square relative to the width of the
    current screen
     */
    public void setJobImageHeights(ImageView[] jobImageViews){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/5;
        for (int i = 0; i < jobImageViews.length; i++)
            jobImageViews[i].getLayoutParams().height = width;
    }

    public void onClick(View view){

    }
}
