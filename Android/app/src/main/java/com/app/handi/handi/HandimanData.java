package com.app.handi.handi;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by christopherlynch on 27/02/2017.
 */

public class HandimanData {

    public String name;
    public ImageView profilePicture;

    public HandimanData(String name, ImageView profilePicture){
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getName(){
        return this.name;
    }

    public ImageView getProfile(){
        return this.profilePicture;
    }
}
