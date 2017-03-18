package com.app.handi.handi.DataTypes;

/**
 * Created by Killian on 16/03/2017.
 */

public class Job {
    private String Description;
    private String Status;
    private boolean isAccepted;

    public Job(){}
    public Job(String Description,String status,boolean isAccepted){
        this.Description = Description;
        this.Status = status;
        this.isAccepted = isAccepted;
    }

    public String getDescription(){
        return this.Description;
    }
    public String getStatus(){
        return this.Status;
    }
}
