package edu.cs.uga.trophyteam;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public abstract class FirebaseDataLists {

    private static List<User> userList;
    private static DatabaseReference mReference;

    public static List<User> getUserListInstance(){
        if (userList == null){
            userList = new ArrayList<>();

            mReference = FirebaseDatabase.getInstance().getReference("users");
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    userList.clear();
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        User user = snapshot.getValue(User.class);
                        userList.add(user);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        return userList;
    }
}
