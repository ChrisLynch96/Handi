package com.app.handi.handi.DataTypes;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by christopherlynch on 27/02/2017.
 */
//Handi data type
public class HandimanData implements Serializable{

    public String name;
    public String email;
    private String uid;
    private String dateOfbirth;
    private String number;
    private String profession;
    private ImageView profilePicture;
    private float rating;

    public HandimanData(){}
    public HandimanData(String name,String email, String dateOfbirth, String Number, String profession, ImageView profilePicture,String uid,float rating){
        this.name = name;
        this.email = email;
        this.dateOfbirth = dateOfbirth;
        this.number = Number;
        this.profession = profession;
        this.profilePicture = profilePicture;
        this.uid = uid;
        this.rating = rating;

    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){ return this.email;}

    public String getdateOfbirth(){ return this.dateOfbirth;}

    public String getNumber(){ return this.number;}

    public String getProfession(){ return this.profession;}

    public ImageView getProfilePicture(){return this.profilePicture;}

    public String getUid(){return this.uid;}

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setProfilePicture(ImageView profilePicture) {
        this.profilePicture = profilePicture;
    }
}
