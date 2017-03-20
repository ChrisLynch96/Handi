package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.User;
import com.app.handi.handi.Firebase.HelperJob;
import com.app.handi.handi.Firebase.HelperUser;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by christopherlynch on 02/03/2017.
 */

//Todo Comment your code!!!

public class JobDescriptionActivity extends AppCompatActivity {
    private EditText inputFirstName, inputLastName, inputTitle, inputDescription,inputAddress ;
    DatabaseReference db;
    FirebaseUser user;
    ArrayList<User> users = new ArrayList<>();
    String profession;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        db = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        HelperUser ref = new HelperUser(db);
        Log.d("id",user.getUid());
//        users = ref.retrieve();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//                e.printStackTrace();
//        }
//        User u = users.get(0);
        inputFirstName = (EditText) findViewById(R.id.activity_job_description_edit_text_first_name);
        //inputFirstName.setText("alrite alrite alrite");
        inputLastName = (EditText) findViewById(R.id.activity_job_description_edit_text_second_name);
        //inputLastName.setText("okokokok");
        inputTitle = (EditText) findViewById(R.id.activity_job_description_text_view_title);
        inputAddress = (EditText) findViewById(R.id.activity_job_description_edit_text_users_address);
        inputDescription = (EditText) findViewById(R.id.activity_job_description_edit_text_users_job_description);
        Bundle bundle = getIntent().getExtras();
        profession = bundle.getString("profession");
        Log.d("prof",profession);
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

        Job job = new Job(Description,"Incomplete",false,title,address,firstName,lastName);
        HelperJob hjob = new HelperJob(db);
        hjob.save(job,user);
        Intent intent = new Intent(this, JobSelectionActivity.class);
        intent.putExtra("profession",profession);
        startActivity(intent);
    }
}
