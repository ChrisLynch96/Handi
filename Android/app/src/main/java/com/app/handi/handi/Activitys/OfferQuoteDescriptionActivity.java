package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.Quote;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.Firebase.HelperQuote;
import com.app.handi.handi.Fragments.HandiHomeFragment;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by christopherlynch on 27/03/2017.
 */

public class OfferQuoteDescriptionActivity extends AppCompatActivity implements HandiHomeFragment.OnFragmentInteractionListener {

    TextView Title,ClientName,Address,Description;
    EditText inputValue;
    Job job;
    DatabaseReference reference;
    FirebaseUser user;
    Intent intent;
    ArrayList<Job> jobs = new ArrayList<>();
    HelperQuote helperQuote;
    HandimanData handimanData;
    ArrayList<Job> Hjobs = new ArrayList<>();
    HelperHandiMan helperHandiMan;
    String HandiName;



    public void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_offer_quote_details);
        reference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        job = (Job)getIntent().getSerializableExtra("LeJobOffer");
        Bundle bundle = getIntent().getExtras();
        HandiName = bundle.getString("HandiName");
        handimanData =(HandimanData)getIntent().getSerializableExtra("Handi");
        jobs = (ArrayList<Job>) getIntent().getSerializableExtra("Jobs");
        Title = (TextView) findViewById(R.id.activity_accept_job_details_text_view_title);
        ClientName = (TextView) findViewById(R.id.activity_accept_job_details_text_view_client_name);
        Address = (TextView) findViewById(R.id.activity_accept_job_details_text_view_address);
        Description = (TextView) findViewById(R.id.activity_accept_job_details_text_view_description);
        helperHandiMan = new HelperHandiMan(reference);
        Hjobs = helperHandiMan.retrieveJob(user);
        //Set the job details texts
        if(job!=null){
            Title.setText(job.getTitle());
            Address.setText(job.getAddress());
            Description.setText(job.getDescription());
            String name = job.getFirstName() +" " +job.getLastName();
            ClientName.setText(name);
        }
        //input formats for the quote
        inputValue = (EditText) findViewById(R.id.editText_offer_quote);
        inputValue.addTextChangedListener(new TextWatcher() {
            private String current = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(current)){
                    inputValue.removeTextChangedListener(this);
                    String cleanString = s.toString().replaceAll("[$,.]","");
                    double parsed;
                    try{
                        parsed = Double.parseDouble(cleanString);
                    }catch (NumberFormatException e){
                        parsed = 0.00;
                    }
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));
                    current=formatted;
                    inputValue.setText(formatted);
                    inputValue.setSelection(formatted.length());
                    inputValue.addTextChangedListener(this);
                }
            }
        });
        intent = new Intent(OfferQuoteDescriptionActivity.this,HandiHomeActivity.class);
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("Jobs",Hjobs);
        intent.putExtras(bundle1);
    }

    public void onClick(View view) {
        //Get the entered value
        String q = inputValue.getText().toString().trim();
        //Make sure the field wasnt left empty
        if(TextUtils.isEmpty(q)){
            Toast.makeText(getApplicationContext(), "Enter quote!", Toast.LENGTH_SHORT).show();
            return;
        }
        //create new Quote
        Quote quote = new Quote(q,job.getUserUid(),user.getUid(),job.getId(),false,HandiName);
        helperQuote = new HelperQuote(reference);
        //Save the quote on the database
        helperQuote.save(quote);
        job.setAccepted(true);
        //Updates the values of the job
        if(job==null)
            Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
        else {
            try {
                reference.child("HandiMen").child(user.getUid()).child("Jobs").child(job.getId()).setValue(job);
                reference.child("Users").child(job.getUserUid()).child("Jobs").child(job.getId()).setValue(job);
            }catch(DatabaseException e){
                e.printStackTrace();
            }
        }
        for(int i=0;i<jobs.size();i++){
            if(jobs.get(i).getId().equals(job.getId()))
                jobs.remove(i);
        }
        jobs.clear();
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
