package com.app.handi.handi.Activitys;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.app.handi.handi.R;
/**
 * Created by christopherlynch on 28/02/2017.
 */


public class HandiManSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private boolean enableCamera;
    private ImageView handiProfilePic;

    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handiman_signup);
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
        //// TODO: 28/02/2017 Implement what happens with the selected data -- Database stuff
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //// Todo: What happens when nothing selected
    }

    public void onClick(View v){
        if (v.getId() == R.id.take_photo_button) {
            dispatchTakePictureIntent();
        }
        else
            startActivity(new Intent(this, MainActivity.class));
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
