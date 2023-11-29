package com.uoft.b07application.ui.viewfeedback;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class F_RecyclerViewAdapter extends RecyclerView.Adapter<F_RecyclerViewAdapter.F_ViewHolder> {
    @NonNull
    @Override
    public F_RecyclerViewAdapter.F_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull F_RecyclerViewAdapter.F_ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class F_ViewHolder extends RecyclerView.ViewHolder{
        public F_ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
