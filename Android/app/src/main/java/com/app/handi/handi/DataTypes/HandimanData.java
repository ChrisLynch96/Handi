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
    public String password;
    public String dateofbirth;
    public String number;
    public String profession;
    public ImageView profilePicture;

    public HandimanData(){}
    public HandimanData(String name,String email, String password, String dateofbirth, String Number, String profession, ImageView profilePicture){
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateofbirth = dateofbirth;
        this.number = Number;
        this.profession = profession;
        this.profilePicture = profilePicture;
    }

    private String getName(){
        return this.name;
    }

    private String getEmail(){ return this.email;}

    private String getPassword(){ return this.password; }

    private String getDateOfbirth(){ return this.dateofbirth;}

    private String getNumber(){ return this.number;}

    private String getProfession(){ return this.profession;}

    private ImageView getProfilePicture(){
        return this.profilePicture;
    }
}
