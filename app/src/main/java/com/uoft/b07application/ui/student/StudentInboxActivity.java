package com.uoft.b07application.ui.student;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.admin.AnnouncementModel;

import java.util.ArrayList;


public class StudentInboxActivity extends StudentActivity {
    private RecyclerView recyclerView;
    private AnnouncementAdapter adapter;
    private ArrayList<AnnouncementModel> announcementList =new ArrayList<>();

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("announcements");

    @Override
    protected int setLayoutId() {
        return R.layout.activity_student_inbox;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.student_inbox_drawer_layout);
        navigationView = findViewById(R.id.nav_student_inbox_view);
        toolbar = findViewById(R.id.student_inbox_toolbar);
    }
    @Override
    public void setButtonListeners(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        recyclerView = findViewById(R.id.announcement_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AnnouncementAdapter(this, announcementList, intent.getStringExtra("username"));

        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    AnnouncementModel announcement= dataSnapshot.getValue(AnnouncementModel.class);
                    announcementList.add(announcement);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



}