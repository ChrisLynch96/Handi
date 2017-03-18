package com.app.handi.handi.Firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.handi.handi.Activitys.HandiManSignupActivity;
import com.app.handi.handi.DataTypes.HandimanData;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class HelperHandiMan {
    DatabaseReference db;
    Boolean saved;
    ArrayList<HandimanData> HandiMen = new ArrayList<>();
    ArrayList<String> Id = new ArrayList<>();

    public HelperHandiMan(){}
    public HelperHandiMan(DatabaseReference db){
        this.db =  db;
    }

    public Boolean save(HandimanData handimanData, FirebaseUser user){
        if(handimanData==null)
            saved = false;
        else{
            try{
                db.child("HandiMen").child(handimanData.getProfession()).child(user.getUid()).setValue(handimanData);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
    public ArrayList<String> getList(){
        Log.d("ok","greatSuccess");
        return Id;
    }
    private void fetchData(DataSnapshot dataSnapshot){
        HandiMen.clear();
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
        for(DataSnapshot child : children){
            HandimanData handiman = child.getValue(HandimanData.class);
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
}
