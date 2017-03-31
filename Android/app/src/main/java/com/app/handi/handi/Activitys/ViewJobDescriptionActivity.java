package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by christopherlynch on 27/03/2017.
 */

public class ViewJobDescriptionActivity extends AppCompatActivity {

    TextView Title,ClientName,Address,Description,Status,Accepted;
    int jobState = 0;
    Button jobDoneButton;
    Job job;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_description);
        job = (Job)getIntent().getSerializableExtra("LeJob");
        Bundle bundle = getIntent().getExtras();
        jobState= bundle.getInt("ViewButton");
        String A = "Accepted";
        String Na = "Not Accepted";
        Title = (TextView) findViewById(R.id.activity_view_job_description_text_view_title);
        ClientName = (TextView) findViewById(R.id.activity_view_job_description_text_view_client_name);
        Address = (TextView) findViewById(R.id.activity_view_job_description_text_view_address);
        Description = (TextView) findViewById(R.id.activity_view_job_description_text_view_description);
        Status = (TextView) findViewById(R.id.activity_view_job_description_text_view_status);
        Accepted = (TextView) findViewById(R.id.activity_view_job_description_text_view_accepted);
        jobDoneButton = (Button) findViewById(R.id.activity_view_job_description_button_job_done);
        if(job!=null) {
            Title.setText(job.getTitle());
            Address.setText(job.getAddress());
            Description.setText(job.getDescription());
            String name = job.getFirstName() +" " +job.getLastName();
            ClientName.setText(name);
            Status.setText(job.getStatus());
            if(job.isAccepted())
                Accepted.setText(A);
            else
                Accepted.setText(Na);
        }
        if (jobState == 0)
            jobDoneButton.setVisibility(View.GONE);
        else if(jobState==1)
            jobDoneButton.setVisibility(View.VISIBLE);
    }

    public void onClick(View view){
        if (view.getId() == R.id.activity_view_job_description_button_job_done){
            Intent intent = new Intent(ViewJobDescriptionActivity.this,RatingsPopUpWindow.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Job",job);
            intent.putExtras(bundle);
            startActivity(intent);
            jobDoneButton.setVisibility(View.GONE);
        }
    }
}
