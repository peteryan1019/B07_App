package com.uoft.b07application.ui.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.viewfeedback.F_RecyclerViewAdapter;

public class FeedbackBottomSheetFragment extends BottomSheetDialogFragment {
    private RecyclerView bottomSheetRecyclerView;
    private F_RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin_view_feedback, container, false);
        bottomSheetRecyclerView = view.findViewById(R.id.bottomSheetRecyclerView);
        adapter = new F_RecyclerViewAdapter();
        return view;
    }
}