package com.app.handi.handi.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.R;

/**
 * Created by christopherlynch on 27/03/2017.
 */

public class ViewJobDescriptionActivity extends AppCompatActivity {

    TextView Title,ClientName,Address,Description,Status,Accepted;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_description);
        Job job = (Job)getIntent().getSerializableExtra("LeJob");
        String A = "Accepted";
        String Na = "Not Accepted";
        Title = (TextView) findViewById(R.id.activity_view_job_description_text_view_title);
        ClientName = (TextView) findViewById(R.id.activity_view_job_description_text_view_client_name);
        Address = (TextView) findViewById(R.id.activity_view_job_description_text_view_address);
        Description = (TextView) findViewById(R.id.activity_view_job_description_text_view_description);
        Status = (TextView) findViewById(R.id.activity_view_job_description_text_view_status);
        Accepted = (TextView) findViewById(R.id.activity_view_job_description_text_view_accepted);
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
    }
}
