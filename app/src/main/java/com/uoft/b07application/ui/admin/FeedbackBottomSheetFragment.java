package com.uoft.b07application.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.viewfeedback.F_RecyclerViewAdapter;

public class FeedbackBottomSheetFragment extends BottomSheetDialogFragment {
    String eventName;
    private TextView eventNameView;
    private RecyclerView bottomSheetRecyclerView;
    private F_RecyclerViewAdapter adapter;

    public FeedbackBottomSheetFragment(String eventName) {
        this.eventName = eventName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_view_feedback, container, false);
        eventNameView = view.findViewById(R.id.event_name_header);
        eventNameView.setText(eventName);
        bottomSheetRecyclerView = view.findViewById(R.id.bottomSheetRecyclerView);
        adapter = new F_RecyclerViewAdapter();
        bottomSheetRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomSheetRecyclerView.setAdapter(adapter);
        return view;
    }

    void setEventNameView(String eventName){
        eventNameView.setText("Event Name");
    }
}