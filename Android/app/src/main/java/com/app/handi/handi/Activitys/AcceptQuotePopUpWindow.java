package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.Quote;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcceptQuotePopUpWindow extends AppCompatActivity {
    Button AcceptButton;
    Job job;
    Quote quote;
    DatabaseReference reference;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_quote_pop_up_window);
        reference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        job = (Job) getIntent().getSerializableExtra("Job");
        quote  = (Quote) getIntent().getSerializableExtra("Quote");
        AcceptButton = (Button) findViewById(R.id.activity_accept_quote_pop_up_window_button);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.3),(int) (height*.1));
    }
    public void onClick(View view){
        if(view.getId() == R.id.activity_accept_quote_pop_up_window_button){
            job.setQuoteAccepted(true);
            quote.setIsqAccepted(true);
            reference.child("HandiMen").child(job.getHandiUid()).child("Jobs").child(job.getId()).setValue(job);
            reference.child("Users").child(user.getUid()).child("Jobs").child(job.getId()).setValue(job);
            reference.child("HandiMen").child(job.getHandiUid()).child("Quotes").child(job.getId()).setValue(quote);
            reference.child("Users").child(user.getUid()).child("Quotes").child(job.getId()).child(job.getHandiUid()).setValue(quote);
            startActivity(new Intent(AcceptQuotePopUpWindow.this,UserHomeActivity.class));
        }
    }
}