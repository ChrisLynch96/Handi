package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MoreQuotesPopUpWindow extends AppCompatActivity {
    Button ReqQuotesButton;
    Job job;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_quotes_pop_up_window);
        reference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        job = (Job) getIntent().getSerializableExtra("Job");
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("HandiUid");
        ReqQuotesButton = (Button) findViewById(R.id.activity_more_quotes_pop_up_window_button);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.3),(int) (height*.1));
    }
    public void onClick(View view){
        if(view.getId()==R.id.activity_more_quotes_pop_up_window_button){
            HelperHandiMan helperHandiMan = new HelperHandiMan(reference);
            job.setAccepted(false);
            helperHandiMan.saveJob(job,uid,job.getId());
            startActivity(new Intent(MoreQuotesPopUpWindow.this,UserHomeActivity.class));
        }
    }
}
