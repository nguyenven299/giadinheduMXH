package com.example.View.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Controller.FirebaseRealtime.Chat.ReadChatList;
import com.example.Controller.FirebaseRealtime.User.InsertDataUserRealtime;
import com.example.Controller.FirebaseRealtime.User.ReadDataUserRealtime;
import com.example.View.Adapter.UserAdapter;
import com.example.Model.ChatList;
import com.example.Model.Users;
import com.example.mxh_gdu3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Users> usersList;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private List<ChatList> stringList;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stringList = new ArrayList<>();

//        databaseReference = FirebaseDatabase.getInstance().getReference("ChatList").child(firebaseUser.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                stringList.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    ChatList chatList = snapshot.getValue(ChatList.class);
//                    stringList.add(chatList);
//                }
//                nguoiDungListChat();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        databaseReference = FirebaseDatabase.getInstance().getReference("ChatListReceiver").child(firebaseUser.getUid());
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    final ChatList chatList = snapshot.getValue(ChatList.class);
//                    Log.d("keyData", "onDataChange: " + chatList.getUid());
//                    final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("ChatList")
//                            .child(firebaseUser.getUid()).child(chatList.getUid());
//                    databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if (!dataSnapshot.exists()) {
//                                databaseReference1.child("Uid").setValue(chatList.getUid());
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        ReadChatList.getInstance().ReadUserChatList(firebaseUser.getUid(), new ReadChatList.IreadChatList() {
//            @Override
//            public void onSuccess(ChatList chatList) {
//                stringList.add(chatList);
//            }
//
//            @Override
//            public void onFail(String Fail) {
//
//            }
//        });

//        ReadDataUserRealtime.getInstance().ReadDataUser(stringList, firebaseUser.getUid(), new ReadDataUserRealtime.IreadDataUserRealtime() {
//            @Override
//            public void onSuccess(List<Users> usersList) {
//
//                userAdapter = new UserAdapter(getContext(), usersList);
//                recyclerView.setAdapter(userAdapter);
//            }
//
//            @Override
//            public void onFail(String Fail) {
//
//            }
//        });
        ReadChatList.getInstance().ReadUserChatList(firebaseUser.getUid(), new ReadChatList.IreadChatList() {
            @Override
            public void onSuccess(List<Users> usersList) {
                userAdapter = new UserAdapter(getContext(), usersList);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onFail(String Fail) {

            }
        });
        InsertDataUserRealtime.getInstance().InserDataUserRealtime(new InsertDataUserRealtime.IinsertDataUser() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        return view;
    }


}
