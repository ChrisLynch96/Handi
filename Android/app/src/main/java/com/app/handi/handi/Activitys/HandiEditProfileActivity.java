package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.app.handi.handi.R;

/**
 * Created by christopherlynch on 19/03/2017.
 */

public class HandiEditProfileActivity extends AppCompatActivity {

    private ImageView handiProfilePic;

    public void onCreate(Bundle savedBundleInstance){
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.activity_handi_edit_profile);
        handiProfilePic = (ImageView) findViewById(R.id.activity_handi_edit_profile_handi_picture_image);
    }

    public void onClick(View view){
        if (view.getId() == R.id.activity_handi_edit_profile_button_take_photo){
            dispatchTakePictureIntent();
        }
        else
            startActivity(new Intent(this, HandiHomeActivity.class));
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
