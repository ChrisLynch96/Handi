package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.Fragments.HandiHomeFragment;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by christopherlynch on 27/03/2017.
 */

public class AcceptJobDescriptionActivity extends AppCompatActivity implements HandiHomeFragment.OnFragmentInteractionListener {

    TextView Title,ClientName,Address,Description;
    Job job;
    DatabaseReference reference;
    FirebaseUser user;
    ArrayList<Job> jobs = new ArrayList<>();


    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_accept_job_details);
        reference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        job = (Job)getIntent().getSerializableExtra("LeJobOffer");
        jobs = (ArrayList<Job>) getIntent().getSerializableExtra("Jobs");
        Title = (TextView) findViewById(R.id.activity_accept_job_details_text_view_title);
        ClientName = (TextView) findViewById(R.id.activity_accept_job_details_text_view_client_name);
        Address = (TextView) findViewById(R.id.activity_accept_job_details_text_view_address);
        Description = (TextView) findViewById(R.id.activity_accept_job_details_text_view_description);
        if(job!=null){
            Title.setText(job.getTitle());
            Address.setText(job.getAddress());
            Description.setText(job.getDescription());
            String name = job.getFirstName() +" " +job.getLastName();
            ClientName.setText(name);
        }
    }

    public void onClick(View view) {
        job.setAccepted(true);
        reference.child("HandiMen").child(user.getUid()).child("Jobs").child(job.getId()).setValue(job);
        for(int i=0;i<jobs.size();i++){
            if(jobs.get(i).getId().equals(job.getId()))
                jobs.remove(i);
        }
        jobs.clear();
       startActivity(new Intent(AcceptJobDescriptionActivity.this,HandiHomeActivity.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
