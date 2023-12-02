package com.uoft.b07application.ui.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.student.Complaint;
import com.uoft.b07application.ui.student.ComplaintsAdapter;

import java.util.ArrayList;

public class AdminReviewCommentsActivity extends AdminActivity {
    private RecyclerView recyclerView;
    private ComplaintsAdapter adapter;
    private ArrayList<Complaint> complaintsList=new ArrayList<>();

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
        recyclerView = findViewById(R.id.recycler1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        Log.d("Reversed List", complaintsList.toString());
        adapter = new ComplaintsAdapter(this, complaintsList);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot datasnapshot : snapshot.getChildren()){
                    Complaint complaint = datasnapshot.getValue(Complaint.class);
                    complaintsList.add(complaint);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
