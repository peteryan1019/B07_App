package com.uoft.b07application.ui.admin;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.student.Complaint;
import com.uoft.b07application.ui.student.ComplaintsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminReviewCommentsActivity extends AdminActivity {
    private RecyclerView recyclerView;
    private ComplaintsAdapter adapter;
    private List<Complaint> complaintsList = new ArrayList<>();

    DatabaseReference reference= FirebaseDatabase.getInstance().getReference("complaints");

    @Override
    public int setLayoutId() {
        return R.layout.activity_admin_review_comments;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.admin_review_comments_drawer_layout);
        navigationView = findViewById(R.id.nav_admin_review_comments_view);
        toolbar = findViewById(R.id.admin_review_comments_toolbar);
    }
    @Override
    protected void setButtonListeners(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admin_review_comments);
//        recyclerView = findViewById(R.id.admin_review_comments_recycler_view);
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Complaint> options
                = new FirebaseRecyclerOptions.Builder<Complaint>()
                .setQuery(reference, Complaint.class)
                .build();
        adapter = new ComplaintsAdapter(options);
        recyclerView.setAdapter(adapter);

    }

//        DatabaseReference complaintsRef = FirebaseDatabase.getInstance().getReference("complaints");
//        complaintsRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
//                String key = dataSnapshot.getKey();
//                Complaint c = dataSnapshot.getValue(Complaint.class);
//                String message = c.getMessage();
//                String id = c.getId();
//                complaintsList.add(new Complaint(id, message));
//                adapter.notifyItemInserted(complaintsList.size() - 1);
//            }

            // Function to tell the app to start getting
            // data from database on starting of the activity
             protected void onStart()
            {
                super.onStart();
                adapter.startListening();
            }

            // Function to tell the app to stop getting
            // data from database on stopping of the activity
             protected void onStop()
            {
                super.onStop();
                adapter.stopListening();
            }

//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
//                // Get the complaint ID and updated message
//                String key = dataSnapshot.getKey();
//                Complaint c = dataSnapshot.child(key).getValue(Complaint.class);
//                String message = c.getMessage();
//                String complaintID = c.getId();
//
//                // Find the index of the complaint with this ID in the list
//                int complaintIndex = -1;
//                for (int i = 0; i < complaintsList.size(); i++) {
//                    if (complaintsList.get(i).getId().equals(complaintID)) {
//                        complaintIndex = i;
//                        break;
//                    }
//                }
//
//                // If found, update the complaint message and notify the adapter
//                if (complaintIndex > -1) {
//                    complaintsList.get(complaintIndex).setMessage(message);
//                    adapter.notifyItemChanged(complaintIndex);
//                }
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot snapshot, String str) {
//
//            }
//
//            public void onCancelled(DatabaseError e) {
//
//            }


            // ... Implement other methods as needed
//        });
//    }
//    @Override
//    public int setLayoutId() {
//        return R.layout.activity_admin_review_comments;
//    }
//    @Override
//    protected void setMenu(){
//        drawerLayout = findViewById(R.id.admin_review_comments_drawer_layout);
//        navigationView = findViewById(R.id.nav_admin_review_comments_view);
//        toolbar = findViewById(R.id.admin_review_comments_toolbar);
//    }
//    @Override
//    protected void setButtonListeners(){
//
//    }
}
