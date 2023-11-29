package com.uoft.b07application.ui.admin;

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
import com.uoft.b07application.ui.event.E_RecyclerViewAdapter;
import com.uoft.b07application.ui.event.EventDialog;
import com.uoft.b07application.ui.event.EventModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AdminEventActivity extends AdminActivity {
    //For Debug
    private static final String TAG = "AdminEvent";
    final DatabaseReference eventRef = FirebaseDatabase.getInstance().getReference().
            child("events").getRef();
    ImageButton scheduleButton;
    private RecyclerView recyclerView;
    private E_RecyclerViewAdapter adapter;
    private ArrayList<EventModel> events = new ArrayList<EventModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.admin_event_recycler_view);
        adapter = new E_RecyclerViewAdapter(this, events, true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        resetEvents();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin_event;

    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.admin_event_drawer_layout);
        navigationView = findViewById(R.id.nav_event_view);
        toolbar = findViewById(R.id.admin_event_toolbar);
    }
    @Override
    protected void setButtonListeners(){
        scheduleButton = findViewById(R.id.schedule_button);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    void openDialog(){
        EventDialog dialog = new EventDialog();
        dialog.show(getSupportFragmentManager(), "event dialog");
    }
    private void resetEvents() {
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                events.clear();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    String key = childSnapshot.getKey();
                    HashMap<String, String> childHashMap = (HashMap<String, String>) childSnapshot.getValue();
                    childHashMap.put("key", key);
                    EventModel childAnModel = new EventModel(childHashMap);
                    events.add(childAnModel);
                    Log.d(TAG, "key" + key + "values: " + childHashMap);
                }
                Collections.reverse(events);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "size" + events.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "fail to read event hash map");
            }
        });
    }
}