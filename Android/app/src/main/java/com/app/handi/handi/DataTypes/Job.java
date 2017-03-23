package com.app.handi.handi.DataTypes;

/**
 * Created by Killian on 16/03/2017.
 */

public class Job {
    private String Description;
    private String Status;
    private boolean isAccepted;
    private String Title;
    private String Address;
    private String FirstName;
    private String LastName;
    private String id;

    public Job(){}
    public Job(String Description,String status,boolean isAccepted,String Title,String Address,String FirstName,String LastName,String id){
        this.Title = Title;
        this.Address = Address;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Description = Description;
        this.Status = status;
        this.isAccepted = isAccepted;
        this.id = id;
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
}
