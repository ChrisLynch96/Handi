package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.R;

public class HandiProfileView extends AppCompatActivity {
    TextView title, Name, Number, Email;
    RatingBar ratingBar;
    Button button;
    Job job;
    boolean moreQuotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handi_profile_view);
        job = (Job)getIntent().getSerializableExtra("Job");
        final Bundle bundle = getIntent().getExtras();
        moreQuotes = bundle.getBoolean("moreQuotes");
        String name = bundle.getString("HandiName");
        String number = bundle.getString("HandiPhone");
        String email = bundle.getString("HandiEmail");
        float rating = bundle.getFloat("Rating");
        final String uid = bundle.getString("HandiUid");
        final String prof = bundle.getString("HandiProf");
        title = (TextView) findViewById(R.id.handi_view_profile_header);
        Name = (TextView) findViewById(R.id.handi_view_profile_name);
        Number = (TextView) findViewById(R.id.handi_view_profile_number);
        Email = (TextView) findViewById(R.id.handi_view_profile_email);
        ratingBar = (RatingBar) findViewById(R.id.hanid_view_profile_rating_bar);
        String set1 = name + "'s Profile";
        //Set the texts to the Handi's details.
        title.setText(set1);
        Name.setText(name);
        Number.setText(number);
        Email.setText(email);
        ratingBar.setRating(rating);
        button = (Button) findViewById(R.id.handi_view_profile_button_offer_job);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Take user to the next screen depending on the boolean value
                if(moreQuotes){
                    Intent intent = new Intent(HandiProfileView.this,MoreQuotesPopUpWindow.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("Job",job);
                    intent.putExtras(bundle1);
                    intent.putExtra("HandiUid", uid);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(HandiProfileView.this, JobDescriptionActivity.class);
                    intent.putExtra("HandiUid", uid);
                    intent.putExtra("HandiProf", prof);
                    startActivity(intent);
                }
            }
        });
    }
}
