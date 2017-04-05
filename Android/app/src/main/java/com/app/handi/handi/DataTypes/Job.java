package com.app.handi.handi.DataTypes;

import java.io.Serializable;

/**
 * Created by Killian on 16/03/2017.
 */
//Job data type
public class Job implements Serializable  {
    private String Description;
    private String Status;
    private boolean isAccepted;
    private String Title;
    private String Address;
    private String FirstName;
    private String LastName;
    private String id;
    private String userUid;
    private String HandiUid;
    private Boolean quoteAccepted;

    public Job(){}
    public Job(String Description,String status,boolean isAccepted,String Title,String Address
            ,String FirstName,String LastName,String id,String userUid,String HandiUid,Boolean quoteAccepted){
        this.Title = Title;
        this.Address = Address;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Description = Description;
        this.Status = status;
        this.isAccepted = isAccepted;
        this.id = id;
        this.userUid = userUid;
        this.HandiUid = HandiUid;
        this.quoteAccepted = quoteAccepted;
    }


    public String getTitle(){return this.Title;}
    public String getAddress(){return this.Address;}
    public String getFirstName(){return this.FirstName;}
    public String getLastName(){return this.LastName;}
    public String getDescription(){
        return this.Description;
    }
    public String getStatus(){
        return this.Status;
    }
    public String getId() {return this.id;}

    public Boolean getQuoteAccepted() {
        return quoteAccepted;
    }

    public String getHandiUid() {
        return HandiUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setQuoteAccepted(Boolean quoteAccepted) {
        this.quoteAccepted = quoteAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public void setHandiUid(String handiUid) {
        HandiUid = handiUid;
    }
}
