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
//Class to help retieve and save data Handi data
public class HelperHandiMan {
    private DatabaseReference db;
    private Boolean saved;
    private ArrayList<HandimanData> HandiMen = new ArrayList<>();
    private ArrayList<Job> jobs = new ArrayList<>();

    public HelperHandiMan(){}
    public HelperHandiMan(DatabaseReference db){
        this.db =  db;
    }

    public Boolean saveInfo(HandimanData handimanData, FirebaseUser user){
        if(handimanData==null)
            saved = false;
        else{
            try{
                db.child("HandiMen").child(user.getUid()).child("Info").setValue(handimanData);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    public Boolean saveJob(Job job,String uid,String id){
        if(job==null)
            saved = false;
        else{
            try{
                db.child("HandiMen").child(uid).child("Jobs").child(id).setValue(job);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    private void fetchData(DataSnapshot dataSnapshot,String profession){
        HandiMen.clear();
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
        for(DataSnapshot child : children){
            HandimanData handiman = child.child("Info").getValue(HandimanData.class);
            if(profession.equals(handiman.getProfession()))
                HandiMen.add(handiman);
            Log.d("stuff","storing");
        }
    }
    public ArrayList<HandimanData> retrieve(final String profession){
        db.child("HandiMen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot,profession);
                Log.d("called","inside");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return HandiMen;
    }
    private void fetchDataJob(DataSnapshot dataSnapshot){
        jobs.clear();
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
        for(DataSnapshot child : children){
            String key = child.getKey();
            Log.d("yo",key);
            Job job = child.getValue(Job.class);
            jobs.add(job);
        }
    }
    public ArrayList<Job> retrieveJob(FirebaseUser user){
      db.child("HandiMen").child(user.getUid()).child("Jobs").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              fetchDataJob(dataSnapshot);
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });
        return jobs;
    }
}
