package com.app.handi.handi;

/**
 * Created by christopherlynch on 27/02/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class JobSelectionActivity extends AppCompatActivity {

    private RecyclerView handiMenRecyclerView;
    private LinearLayoutManager mLinearLayoutManger;
    private RecyclerView jobsRecyclerView;
    public ArrayList<HandimanData> handiMen;
    private ChooseHandimanRowAdapter handiMenAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_selection);
        handiMenRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_handimen);
        mLinearLayoutManger = new LinearLayoutManager(this);
        handiMenRecyclerView.setLayoutManager(mLinearLayoutManger);

        handiMen = new ArrayList<HandimanData>();
        handiMenAdapter = new ChooseHandimanRowAdapter(handiMen);
        handiMenRecyclerView.setAdapter(handiMenAdapter);
    }

    public void fillArrayList(){
        //ToDo put in some test images to check recycler view is working

    }
}
