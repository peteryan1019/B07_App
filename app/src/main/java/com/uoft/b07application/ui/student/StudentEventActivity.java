package com.uoft.b07application.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.uoft.b07application.ui.event.EventModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StudentEventActivity extends StudentActivity {
    private static final String TAG = "StudentEvent";
    final DatabaseReference eventRef = FirebaseDatabase.getInstance().getReference().
            child("events").getRef();
    private RecyclerView recyclerView;
    private E_RecyclerViewAdapter adapter;
    private ArrayList<EventModel> events = new ArrayList<EventModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.student_event_recycler_view);
        Intent intent = getIntent();
        adapter = new E_RecyclerViewAdapter(this, events, false,
                intent.getStringExtra("name"), intent.getStringExtra("email"));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        resetEvents();
    }



    @Override
    protected int setLayoutId() {
        return R.layout.activity_student_event;

    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.student_event_drawer_layout);
        navigationView = findViewById(R.id.nav_student_event_view);
        toolbar = findViewById(R.id.student_event_toolbar);
    }
    @Override
    public void setButtonListeners(){

    }
    private void resetEvents() {
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                events.clear();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    String key = childSnapshot.getKey();
                    HashMap<String, Object> childHashMap = (HashMap<String, Object>) childSnapshot.getValue();
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
