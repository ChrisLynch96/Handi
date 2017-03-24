package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.User;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.Firebase.HelperUser;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by christopherlynch on 02/03/2017.
 */

//Todo Comment your code!!!

public class JobDescriptionActivity extends AppCompatActivity {
    private EditText inputFirstName, inputLastName, inputTitle, inputDescription,inputAddress ;
    DatabaseReference db;
    FirebaseUser user;
    ArrayList<User> users = new ArrayList<>();
    String uid;
    String prof;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        db = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        HelperUser ref = new HelperUser(db);
        Log.d("id",user.getUid());
        Bundle bundle = getIntent().getExtras();
        uid = bundle.getString("HandiUid");
        prof = bundle.getString("HandiProf");
        inputFirstName = (EditText) findViewById(R.id.activity_job_description_edit_text_first_name);
        inputLastName = (EditText) findViewById(R.id.activity_job_description_edit_text_second_name);
        inputTitle = (EditText) findViewById(R.id.activity_job_description_edit_text_title);
        inputAddress = (EditText) findViewById(R.id.activity_job_description_edit_text_users_address);
        inputDescription = (EditText) findViewById(R.id.activity_job_description_edit_text_users_job_description);
    }
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }
    public void onClick(View v){
        String firstName = inputFirstName.getText().toString().trim();
        String lastName = inputLastName.getText().toString().trim();
        String title = inputTitle.getText().toString().trim();
        String address = inputAddress.getText().toString().trim();
        String Description = inputDescription.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getApplicationContext(), "Enter title!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Description)) {
            Toast.makeText(getApplicationContext(), "Enter description!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(address)) {
            Toast.makeText(getApplicationContext(), "Enter address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(firstName)) {
            Toast.makeText(getApplicationContext(), "Enter first name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(lastName)) {
            Toast.makeText(getApplicationContext(), "Enter last name!", Toast.LENGTH_SHORT).show();
            return;
        }

        Job job = new Job(Description,"Incomplete",false,title,address,firstName,lastName,getSaltString());
        HelperUser helperUser = new HelperUser(db);
        HelperHandiMan helperHandiMan = new HelperHandiMan(db);
        helperUser.saveJob(job,user,job.getId());
        helperHandiMan.saveJob(job,uid,prof,job.getId());
        startActivity(new Intent(JobDescriptionActivity.this, UserHomeActivity.class));
    }
}
