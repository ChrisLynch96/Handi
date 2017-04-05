package com.app.handi.handi.DataTypes;

/**
 * Created by Killian on 20/03/2017.
 */
//User data type
public class User {
    private String FirstName;
    private String LastName;
    private String email;
    private String uid;

    public User(){}
    public User(String FirstName,String LastName,String email,String uid){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = email;
        this.uid = uid;
    }
    public String getFirstName(){
        return this.FirstName;
    }
    public String getLastName(){return this.LastName;}
    public String getEmail() {return email;}
    public String getUid() {return uid;}
}
