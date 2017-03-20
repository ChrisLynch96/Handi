package com.app.handi.handi.Firebase;

import com.app.handi.handi.DataTypes.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Killian on 20/03/2017.
 */

public class HelperUser {
    DatabaseReference db;
    Boolean saved;

    public HelperUser(){}
    public HelperUser(DatabaseReference db){
        this.db = db;
    }

    public Boolean save(User users, FirebaseUser user){
        if(users==null)
            saved = false;
        else{
            try{
                db.child("Users").child(user.getUid()).setValue(users);
                saved = true;
            }catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }
}
