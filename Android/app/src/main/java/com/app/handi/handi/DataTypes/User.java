package com.app.handi.handi.DataTypes;

/**
 * Created by Killian on 20/03/2017.
 */

public class User {
    private String FirstName;
    private String LastName;

    public User(){}
    public User(String FirstName,String LastName){
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    public String getFirstName(){
        return this.FirstName;
    }
    public String getLastName(){
        return this.LastName;
    }
}
