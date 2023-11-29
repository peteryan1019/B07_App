package com.uoft.b07application.ui.student;

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
        adapter = new E_RecyclerViewAdapter(this, events, false);
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
//        String[] events = {"Event 1", "Event 2", "Event 3"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);
//
//        ListView listView = findViewById(R.id.eventListView);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Launch the StudentFeedback activity
//                Intent intent = new Intent(StudentEventActivity.this, StudentFeedback.class);
//                // Pass any data you want to the form activity, for example, the selected event
//                intent.putExtra("event_name", events[position]);
//
//                // Explicitly set the component to ensure it opens the correct activity
//                intent.setComponent(new ComponentName(StudentEventActivity.this, StudentFeedback.class));
//
//                startActivity(intent);
//            }
//        });
    }
    private void resetEvents() {
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                events.clear();
                for(DataSnapshot childSnapshot: snapshot.getChildren()){
                    String key = childSnapshot.getKey();
                    HashMap<String, String> childHashMap = (HashMap<String, String>) childSnapshot.getValue();
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
