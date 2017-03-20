package com.app.handi.handi.Firebase;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Killian on 20/03/2017.
 */

public class HelperJob {
    DatabaseReference db;
    boolean saved=false;
    public HelperJob(){}
    public HelperJob(DatabaseReference db){
        this.db = db;
    }
    public Boolean save(Job job, FirebaseUser user){
        if(job==null)
            saved = false;
        else{
            try{
                db.child("Jobs").child(user.getUid()).setValue(job);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
}
