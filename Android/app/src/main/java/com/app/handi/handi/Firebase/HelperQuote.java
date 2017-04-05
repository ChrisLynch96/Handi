package com.app.handi.handi.Firebase;

import android.util.Log;

import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.DataTypes.Quote;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Killian on 01/04/2017.
 */
//Class to help retrieve and save quote data
public class HelperQuote {
    private DatabaseReference db;
    private ArrayList<Quote> quotes = new ArrayList<>();

    public HelperQuote(){}
    public HelperQuote(DatabaseReference db){
        this.db = db;
    }
    public Boolean save(Quote quote){
        Boolean saved;
        if(quote==null)
            saved = false;
        else{
            try{
                db.child("HandiMen").child(quote.getHandiUid()).child("Quotes").child(quote.getJobId()).setValue(quote);
                db.child("Users").child(quote.getUserUid()).child("Quotes").child(quote.getJobId()).child(quote.getHandiUid()).setValue(quote);
                saved = true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved =false;
            }
        }
        return saved;
    }
    private void fetchData(DataSnapshot dataSnapshot){
        quotes.clear();
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
        for(DataSnapshot child : children){
            Quote q = child.getValue(Quote.class);
            quotes.add(q);
        }

    }
    public ArrayList<Quote> retrieve(FirebaseUser user, Job job){
        db.child("Users").child(user.getUid()).child("Quotes").child(job.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return quotes;
    }
}
