package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.handi.handi.Adapters.DisplayQuotesAdapter;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.Quote;
import com.app.handi.handi.Firebase.HelperQuote;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by christopherlynch on 27/03/2017.
 */

public class ViewJobDescriptionActivity extends AppCompatActivity {

    TextView Title,ClientName,Address,Description,Status,Accepted,Quotes;
    int jobState = 0;
    Button jobDoneButton,anotherQuote;
    Job job;
    ListView listView;
    DisplayQuotesAdapter displayQuotesAdapter;
    DatabaseReference db;
    FirebaseUser user;
    HandimanData handimanData;
    HelperQuote helperQuote;
    ArrayList<Quote> quotes = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job_description);
        db = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        job = (Job)getIntent().getSerializableExtra("LeJob");
        handimanData =(HandimanData)getIntent().getSerializableExtra("Handi");
        quotes = (ArrayList<Quote>)getIntent().getSerializableExtra("Quotes");
        Bundle bundle = getIntent().getExtras();
        jobState= bundle.getInt("ViewButton");
        final String A = "Accepted";
        final String Na = "Not Accepted";
        final String q = "Quotes";
        Title = (TextView) findViewById(R.id.activity_view_job_description_text_view_title);
        ClientName = (TextView) findViewById(R.id.activity_view_job_description_text_view_client_name);
        Address = (TextView) findViewById(R.id.activity_view_job_description_text_view_address);
        Description = (TextView) findViewById(R.id.activity_view_job_description_text_view_description);
        Status = (TextView) findViewById(R.id.activity_view_job_description_text_view_status);
        Accepted = (TextView) findViewById(R.id.activity_view_job_description_text_view_accepted);
        Quotes = (TextView) findViewById(R.id.activity_view_job_description_text_view_Quotes);
        jobDoneButton = (Button) findViewById(R.id.activity_view_job_description_button_job_done);
        anotherQuote = (Button) findViewById(R.id.activity_view_job_description_button_more_quotes);
        listView = (ListView) findViewById(R.id.activity_view_job_description_listview_quotes);
        helperQuote = new HelperQuote(db);
        //setting the textviews with job details
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
        else
            Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
        if (jobState == 0) {
            jobDoneButton.setVisibility(View.GONE);
            anotherQuote.setVisibility(View.GONE);
        }
        else if(jobState==2) {
            jobDoneButton.setVisibility(View.GONE);
            anotherQuote.setVisibility(View.VISIBLE);
        }
        else {
            anotherQuote.setVisibility(View.GONE);
            jobDoneButton.setVisibility(View.VISIBLE);
        }
        if(jobState==2){
            Quotes.setText(q);
            displayQuotesAdapter = new DisplayQuotesAdapter(quotes,this);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Displaying the list of quotes
            listView.setAdapter(displayQuotesAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Quote quote = (Quote) view.getTag();
                    Intent intent = new Intent(ViewJobDescriptionActivity.this,AcceptQuotePopUpWindow.class);
                    Bundle bundle = new Bundle();
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("Quote",quote);
                    bundle.putSerializable("Job",job);
                    intent.putExtras(bundle1);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

    }

    public void onClick(View view){
        if (view.getId() == R.id.activity_view_job_description_button_job_done){
            Intent intent = new Intent(ViewJobDescriptionActivity.this,RatingsPopUpWindow.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Job",job);
            bundle.putSerializable("Handi",handimanData);
            intent.putExtras(bundle);
            startActivity(intent);
            jobDoneButton.setVisibility(View.GONE);
        }
        else if(view.getId()==R.id.activity_view_job_description_button_more_quotes){
            Intent intent = new Intent(ViewJobDescriptionActivity.this,ChooseHandiTypeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Job",job);
            intent.putExtras(bundle);
            intent.putExtra("moreQuotes",true);
            startActivity(intent);
        }
    }
}
