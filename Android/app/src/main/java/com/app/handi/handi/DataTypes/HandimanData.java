package com.app.handi.handi.DataTypes;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.handi.handi.Activitys.HandiManSignupActivity;

/**
 * Created by christopherlynch on 27/02/2017.
 */

public class HandimanData {

    public String name;
    public String email;
    private String dateofbirth;
    private String number;
    public String profession;
    private ImageView profilePicture;

    public HandimanData(){}
    public HandimanData(String name,String email, String dateofbirth, String Number, String profession, ImageView profilePicture){
        this.name = name;
        this.email = email;
        this.dateofbirth = dateofbirth;
        this.number = Number;
        this.profession = profession;
        this.profilePicture = profilePicture;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){ return this.email;}

    public String getDateOfbirth(){ return this.dateofbirth;}

    public String getNumber(){ return this.number;}

    public String getProfession(){ return this.profession;}

    public ImageView getProfilePicture(){
        return this.profilePicture;
    }
}
