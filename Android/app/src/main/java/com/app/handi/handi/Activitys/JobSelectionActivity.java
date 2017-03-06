package com.app.handi.handi.Activitys;

/**
 * Created by christopherlynch on 27/02/2017.
 */

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.app.handi.handi.Adapters.ChooseHandimanRowAdapter;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.R;

import java.util.ArrayList;

public class JobSelectionActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selection);

        //Creating an array containing the job images so I can just iterate through them.
        ImageView[] jobImageViews = {(ImageView) findViewById(R.id.activity_job_selection_image_view_cleaner), (ImageView) findViewById(R.id.activity_job_selection_image_view_electrician)
                , (ImageView) findViewById(R.id.activity_job_selection_image_view_painter), (ImageView) findViewById(R.id.activity_job_selection_image_view_handiman)
                , (ImageView) findViewById(R.id.activity_job_selection_image_view_plumber)};

        setJobImageHeights(jobImageViews);
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
