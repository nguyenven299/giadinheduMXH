package com.example.View.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Controller.FirebaseRealtime.SocialNetwork.Apdapter.SocialAdapter;
import com.example.Module.Social;
import com.example.mxh_gdu3.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment {
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private ListView listView;
    private ArrayList<Social> socialList;
    private Social social;
    private SocialAdapter socialAdapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    //DATA
    private static ArrayList<Social> mangThongBao = new ArrayList<>();
    private static ArrayList<Social> mangThongBaoTam = new ArrayList<>();
    private boolean ascending = true;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progressBar);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setReverseLayout(true);
        linearLayoutManager1.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager1);
        nhanThongBao();
        hienThiThongBao(view);
        socialList = new ArrayList<Social>();
        return view;
    }

    private void nhanThongBao() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Thong_Bao");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                socialList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Social social1 = dataSnapshot1.getValue(Social.class);
                    socialList.add(social1);
                    String key = dataSnapshot1.getKey();
                    social1.setKey(key);
                }
                socialAdapter = new SocialAdapter(socialList, getContext());
                recyclerView.setAdapter(socialAdapter);
                socialAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Đăng Xuất Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                socialList.clear(); //
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    String key = dataSnapshot1.getKey();
//                    social = dataSnapshot1.getValue(Social.class);
//                    Log.d("ToanBo", "onDataChange: " + social);
//                    assert social != null;
//                    social.setKey(key);
//                    socialAdapter = new SocialAdapter(socialList,getContext());
//                    recyclerView.setAdapter(socialAdapter);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });


//        // Reverse logic
//        for (int i = 0; i < mangThongBao.size() / 2; i++) {
//            Social temporary = mangThongBao.get(i); //temporary là biến tạm lưu giá trị
//            mangThongBao.set(i, mangThongBao.get(mangThongBao.size() - 1 - i)); //lấy giá trị cuối lưu vào đầu
//            mangThongBao.set(mangThongBao.size() - 1 - i, temporary); //lấy giá trị đầu lưu vào cuối
//        }


    }

//    private void sortData(boolean asc) {
//        //SORT ARRAY ASCENDING AND DESCENDING
//        if (asc) {
//            Collections.sort(contactsAdapterSocial);
//        } else {
//            Collections.reverse(contactsAdapterSocial);
//        }
//
//    }

    private void hienThiThongBao(View view) {
//        listView = view.findViewById(R.id.listViewSocialNetwork);
//        contactsAdapterSocial = new ContactsAdapterSocial(getActivity(), R.layout.item);
////        sortData(false);
//        listView.setAdapter(contactsAdapterSocial);
//        recyclerView.setAdapter(SocialAdapter);
    }

}