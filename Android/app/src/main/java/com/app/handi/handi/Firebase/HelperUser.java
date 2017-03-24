package com.app.handi.handi.Firebase;

import android.util.Log;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Killian on 20/03/2017.
 */

public class HelperUser {
    DatabaseReference db;
    Boolean saved;
    ArrayList<Job> user = new ArrayList<>();

    public HelperUser(){}
    public HelperUser(DatabaseReference db){
        this.db = db;
    }

    public Boolean saveInfo(User users, FirebaseUser user){
        if(users==null)
            saved = false;
        else{
            try{
                db.child("Users").child(user.getUid()).child("Info").setValue(users);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    public Boolean saveJob(Job job, FirebaseUser user,String id){
        if(job==null)
            saved = false;
        else{
            try{
                db.child("Users").child(user.getUid()).child("Jobs").child(id).setValue(job);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    private void fetchData(DataSnapshot dataSnapshot){
        user.clear();
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
        for(DataSnapshot child : children){
             Job job  = child.getValue(Job.class);
            user.add(job);
            Log.d("stuff","storing");
        }

    }
    public ArrayList<Job> retrieve(FirebaseUser users){
        db.child("Users").child(users.getUid()).child("Jobs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
                Log.d("called","inside");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return user;
    }
}
