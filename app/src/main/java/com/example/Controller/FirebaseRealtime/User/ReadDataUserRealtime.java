package com.example.Controller.FirebaseRealtime.User;

import androidx.annotation.NonNull;

import com.example.Model.ChatList;
import com.example.Model.Users;
import com.example.View.Adapter.UserAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class ReadDataUserRealtime {
    public static ReadDataUserRealtime instance;

    public static ReadDataUserRealtime getInstance() {
        if (instance == null)
            instance = new ReadDataUserRealtime();
        return instance;
    }

    public interface IreadDataUserRealtime {
        void onSuccess(List<Users> usersList);

        void onFail(String Fail);
    }

    public void ReadDataUser(final List<ChatList> stringList, String uid, final IreadDataUserRealtime ireadDataUserRealtime) {

        final List<Users> usersList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Users users = dataSnapshot1.getValue(Users.class);
                    for (ChatList chatList : stringList) {
                        if (users.getUid().equals(chatList.getUid())) {
                            usersList.add(users);
                        }
                    }
                }
                ireadDataUserRealtime.onSuccess(usersList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}