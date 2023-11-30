package com.uoft.b07application.ui.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.viewfeedback.F_RecyclerViewAdapter;
import com.uoft.b07application.ui.viewfeedback.FeedbackModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FeedbackBottomSheetFragment extends BottomSheetDialogFragment {
    final private String TAG = "viewFeedback";
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ArrayList<FeedbackModel> feedbacks = new ArrayList<>();
    String eventName, eventKey;
    private TextView eventNameView;
    private RecyclerView bottomSheetRecyclerView;
    private F_RecyclerViewAdapter adapter;

    public FeedbackBottomSheetFragment(String eventName, String eventKey) {
        this.eventName = eventName;
        this.eventKey = eventKey;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_view_feedback, container, false);
        eventNameView = view.findViewById(R.id.event_name_header);
        eventNameView.setText(eventName);
        bottomSheetRecyclerView = view.findViewById(R.id.bottomSheetRecyclerView);
        adapter = new F_RecyclerViewAdapter(this.getContext(), feedbacks);
        bottomSheetRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomSheetRecyclerView.setAdapter(adapter);
        resetFeedbacks();
        return view;
    }

    private void resetFeedbacks() {
        final DatabaseReference feedbackRef = databaseReference.child("feedback").getRef();
        feedbackRef.orderByChild("eventKey").equalTo(eventKey).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                HashMap<String, String> childHashMap = (HashMap<String, String>) snapshot.getValue();
                FeedbackModel feedback = new FeedbackModel(childHashMap);
                feedbacks.add(feedback);
                Collections.reverse(feedbacks);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "size" + feedbacks.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "fail to read feedback hash map");
            }
        });
    }
}