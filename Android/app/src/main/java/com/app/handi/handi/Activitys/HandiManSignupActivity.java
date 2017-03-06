package com.app.handi.handi.Activitys;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by christopherlynch on 28/02/2017.
 */


public class HandiManSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean enableCamera;
    private EditText HandiName, HandiDOB, HandiEmail, HandiNo, HandiPassword, HandiConPassword;
    private ProgressBar progressBar;
    private ImageView handiProfilePic;
    private FirebaseAuth auth;
    private HandimanData data;
    private FirebaseUser user;
    private DatabaseReference ref;
    String spin="";

    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handiman_signup);
        auth = FirebaseAuth.getInstance();
        handiProfilePic = (ImageView) findViewById(R.id.handi_picture_image);

        //getSystemAvailableFeatures()
        //Todo check that a phone has a camera. If not disable camera features
        Spinner spinner = (Spinner) findViewById(R.id.job_choice_spinner);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.jobs_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spin = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onClick(View v){
        if (v.getId() == R.id.take_photo_button) {
            dispatchTakePictureIntent();
        }
        else {
            //startActivity(new Intent(this, MainActivity.class));
            HandiName = (EditText) findViewById(R.id.handi_name);
            HandiDOB = (EditText) findViewById(R.id.handi_DOB);
            HandiEmail = (EditText) findViewById(R.id.handi_email);
            HandiNo = (EditText) findViewById(R.id.handi_phone_number);
            HandiPassword = (EditText) findViewById(R.id.handi_password_enter1);
            HandiConPassword = (EditText) findViewById(R.id.handi_password_enter2);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);

            final String email = HandiEmail.getText().toString().trim();
            final String password = HandiPassword.getText().toString().trim();
            String conpassword = HandiConPassword.getText().toString().trim();
            final String name = HandiName.getText().toString().trim();
            final String dateofbirth = HandiDOB.getText().toString().trim();
            final String number = HandiNo.getText().toString().trim();

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


            progressBar.setVisibility(View.VISIBLE);
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(HandiManSignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(HandiManSignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(HandiManSignupActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(HandiManSignupActivity.this, MainActivity.class));
                                data = new HandimanData(name,email,password,dateofbirth,number,spin,handiProfilePic);
                                user = FirebaseAuth.getInstance().getCurrentUser();
                                ref = FirebaseDatabase.getInstance().getReference();
                                ref.child("HandiMen").child(spin).child(user.getUid()).setValue(data);
                                finish();
                            }
                        }
                    });

        }
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Hi", "Hi");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            handiProfilePic.setImageBitmap(imageBitmap);
        }
    }
}
