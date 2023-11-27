package com.uoft.b07application.ui.student;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uoft.b07application.R;

public class StudentEventActivity extends StudentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        String[] events = {"Event 1", "Event 2", "Event 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);

        ListView listView = findViewById(R.id.eventListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the StudentFeedback activity
                Intent intent = new Intent(StudentEventActivity.this, StudentFeedback.class);
                // Pass any data you want to the form activity, for example, the selected event
                intent.putExtra("event_name", events[position]);

                // Explicitly set the component to ensure it opens the correct activity
                intent.setComponent(new ComponentName(StudentEventActivity.this, StudentFeedback.class));

                startActivity(intent);
            }
        });
    }
}
