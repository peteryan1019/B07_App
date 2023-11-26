package com.uoft.b07application.ui.BenStudentPageEvents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;

import com.uoft.b07application.ui.BenStudentPageEvents.StudentFeedback;
import com.uoft.b07application.R;
public class EventPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        // temp events
        String[] events = {"Event 1", "Event 2", "Event 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, events);

        ListView listView = findViewById(R.id.eventListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the StudentFeedback activity
                Intent intent = new Intent(EventPageActivity.this, StudentFeedback.class);
                // Pass any data you want to the form activity, for example, the selected event
                intent.putExtra("event_name", events[position]);

                // Explicitly set the component to ensure it opens the correct activity
                intent.setComponent(new ComponentName(EventPageActivity.this, StudentFeedback.class));

                startActivity(intent);
            }
        });

    }
}