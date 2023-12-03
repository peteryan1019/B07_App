package com.uoft.b07application.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AdminAnnouncementActivity extends AdminActivity {
    private static final String TAG = "AdminAnnouncement";
    final DatabaseReference announcementRef = FirebaseDatabase.getInstance().getReference().
            child("announcements").getRef();
    private ImageButton composeButton;
    private RecyclerView recyclerView;
    private AN_RecyclerViewAdapter adapter;
    private ArrayList<AnnouncementModel> announcementModels = new ArrayList<AnnouncementModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.announcement_recycler_view);
        adapter = new AN_RecyclerViewAdapter(this, announcementModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        resetAnnouncementModels();
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin_announcement;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.announcement_drawer_layout);
        navigationView = findViewById(R.id.nav_announcement_view);
        toolbar = findViewById(R.id.announcement_toolbar);
    }
    @Override
    public void setButtonListeners(){
        composeButton = findViewById(R.id.composeButton);
        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }
    private void openDialog(){
        AnnouncementDialog announcementDialog = new AnnouncementDialog();
        announcementDialog.setSenderUsername(username);
        announcementDialog.setSenderEmail(email);
        announcementDialog.show(getSupportFragmentManager(), "announcement dialog");
    }

    public void resetAnnouncementModels() {
        announcementRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                announcementModels.clear();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    String key = childSnapshot.getKey();
                    HashMap<String, String> childHashMap = (HashMap<String, String>) childSnapshot.getValue();
                    AnnouncementModel childAnModel = new AnnouncementModel(childHashMap);
                    announcementModels.add(childAnModel);
                    Log.d(TAG, "key" + key + "values: " + childHashMap);
                }
                Collections.reverse(announcementModels);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "size" + announcementModels.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "fail to read announcement hash map");
            }
        });
    }
}