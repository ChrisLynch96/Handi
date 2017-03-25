package com.app.handi.handi.Firebase;

import android.util.Log;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HelperHandiMan {
    DatabaseReference db;
    Boolean saved;
    ArrayList<HandimanData> HandiMen = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();

    public HelperHandiMan(){}
    public HelperHandiMan(DatabaseReference db){
        this.db =  db;
    }

    public Boolean saveInfo(HandimanData handimanData, FirebaseUser user){
        if(handimanData==null)
            saved = false;
        else{
            try{
                db.child("HandiMen").child(handimanData.getProfession()).child(user.getUid()).child("Info").setValue(handimanData);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    public Boolean saveJob(Job job,String uid,String prof,String id){
        if(job==null)
            saved = false;
        else{
            try{
                db.child("HandiMen").child(prof).child(uid).child("Jobs").child(id).setValue(job);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    private void fetchData(DataSnapshot dataSnapshot){
        HandiMen.clear();
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
        for(DataSnapshot child : children){
            HandimanData handiman = child.child("Info").getValue(HandimanData.class);
            HandiMen.add(handiman);
            Log.d("stuff","storing");
        }
    }
    public ArrayList<HandimanData> retrieve(String profession){
        db.child("HandiMen").child(profession).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
                Log.d("called","inside");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return HandiMen;
    }
    private void fetchDataJob(DataSnapshot dataSnapshot,FirebaseUser user){
        jobs.clear();
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
        for(DataSnapshot child : children){
            String key = child.getKey();
            if(key.equals(user.getUid())) {
                String st =child.child("Jobs").getKey();
                Log.d("stuff","storing");
                Log.d("lol",st);
                Iterable<DataSnapshot> children2 = child.getChildren();
                for(DataSnapshot child2 : children2){
                    Job job = child2.child("Jobs").getValue(Job.class);
                    //Log.d("lol",key2);
//                    if(key.equals(user.getUid())) {
//                        String st2 =child.getKey();
//                        //Log.d("stuff","storing");
//                        Log.d("lol",st2);
//                    }
                }
            }
            //Job job = child.child(user.getUid()).child("Jobs").getValue(Job.class);
            //jobs.add(job);
        }
    }
    public ArrayList<Job> retrieveJob(final FirebaseUser user){
       db.child("HandiMen").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               fetchDataJob(dataSnapshot,user);
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
        return jobs;
    }
}
