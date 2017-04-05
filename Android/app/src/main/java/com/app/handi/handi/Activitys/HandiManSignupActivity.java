package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.Firebase.HelperHandiMan;
import com.app.handi.handi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by christopherlynch on 28/02/2017.
 */


public class HandiManSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ProgressBar progressBar;
    private ImageView handiProfilePic;
    private FirebaseAuth auth;
    private HandimanData data;
    private FirebaseUser user;
    private DatabaseReference ref;
    HandimanData handimanData;
    String spin="";
    ArrayList<Job> Hjobs = new ArrayList<>();

    boolean saved = false;

    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handiman_signup);
        auth = FirebaseAuth.getInstance();
        handiProfilePic = (ImageView) findViewById(R.id.handi_picture_image);

        //getSystemAvailableFeatures()
        //Todo check that a phone has a camera. If not disable camera features
        Spinner spinner = (Spinner) findViewById(R.id.job_choice_spinner);
        spinner.setOnItemSelectedListener(this);
// Creating an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jobs_array, android.R.layout.simple_spinner_item);
// Specifying the layout to use when the list of choices appears

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    //When an item is selected we get the name of what was selected.
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spin = parent.getItemAtPosition(position).toString();
    }

    //default to the first element
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        spin = parent.getItemAtPosition(0).toString();
    }

    /*
    Determines what was clicked and what should be done
     */
    public void onClick(View v){
        if (v.getId() == R.id.take_photo_button) {
            dispatchTakePictureIntent();
        }
        else {
            //initialise the edittexts
            EditText handiName = (EditText) findViewById(R.id.handi_name);
            EditText handiDOB = (EditText) findViewById(R.id.handi_DOB);
            EditText handiEmail = (EditText) findViewById(R.id.handi_email);
            EditText handiNo = (EditText) findViewById(R.id.handi_phone_number);
            EditText handiPassword = (EditText) findViewById(R.id.handi_password_enter1);
            final EditText handiConPassword = (EditText) findViewById(R.id.handi_password_enter2);
            progressBar = (ProgressBar) findViewById(R.id.activity_handiman_signup_progress_bar_pBar);

            final String email = handiEmail.getText().toString().trim();
            final String password = handiPassword.getText().toString().trim();
            String conpassword = handiConPassword.getText().toString().trim();
            final String name = handiName.getText().toString().trim();
            final String dateofbirth = handiDOB.getText().toString().trim();
            final String number = handiNo.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(dateofbirth)) {
                Toast.makeText(getApplicationContext(), "Enter date of birth!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(number)) {
                Toast.makeText(getApplicationContext(), "Enter number!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(conpassword)) {
                Toast.makeText(getApplicationContext(), "Confirm password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(conpassword)) {
                Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                return;
            }
            //progressbar
            progressBar.setVisibility(View.VISIBLE);
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(HandiManSignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(HandiManSignupActivity.this, "Account Creation:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(HandiManSignupActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                assert user != null;
                                //create new Handi
                                data = new HandimanData(name,email,dateofbirth,number,spin,handiProfilePic,user.getUid());
                                ref = FirebaseDatabase.getInstance().getReference();
                                ref.child("HandiMen").child(user.getUid()).child("Info").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        handimanData = dataSnapshot.getValue(HandimanData.class);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                HelperHandiMan db = new HelperHandiMan(ref);
                                Hjobs = db.retrieveJob(user);
                                //Save the Handi to the database
                                saved = db.saveInfo(data,user);
                                Intent intent = new Intent(HandiManSignupActivity.this,HandiHomeActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("Jobs",Hjobs);
                                bundle.putSerializable("Handi",handimanData);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //If there is a camera registered to the device then launch it's application.
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Setting the image view that represents the profile pic to the picture that was just taken
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            handiProfilePic.setImageBitmap(imageBitmap);
        }
    }
}
