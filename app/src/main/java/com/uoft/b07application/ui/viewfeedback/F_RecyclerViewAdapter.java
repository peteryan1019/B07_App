package com.uoft.b07application.ui.viewfeedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.event.E_RecyclerViewAdapter;
import com.uoft.b07application.ui.event.EventModel;

import java.util.ArrayList;

public class F_RecyclerViewAdapter extends RecyclerView.Adapter<F_RecyclerViewAdapter.F_ViewHolder> {
    Context context;
    ArrayList<FeedbackModel> feedbacks;
    public F_RecyclerViewAdapter(Context context, ArrayList<FeedbackModel> feedbacks) {
        this.context = context;
        this.feedbacks = feedbacks;
    }

    @NonNull
    @Override
    public F_RecyclerViewAdapter.F_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.feedback_recycler_row, parent, false);
        return new F_RecyclerViewAdapter.F_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull F_RecyclerViewAdapter.F_ViewHolder holder, int position) {
        holder.commenterName.setText(feedbacks.get(position).getCommenterName());
        holder.commenterEmail.setText(feedbacks.get(position).getCommenterEmail());
        holder.feedbackComment.setText(feedbacks.get(position).getFeedbackComment());
        holder.rating.setText(feedbacks.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }
    public static class F_ViewHolder extends RecyclerView.ViewHolder{
        TextView commenterName;
        TextView commenterEmail;
        TextView feedbackComment;
        TextView rating;

        public F_ViewHolder(@NonNull View itemView) {
            super(itemView);
            commenterName = itemView.findViewById(R.id.recycler_commenterName);
            commenterEmail = itemView.findViewById(R.id.recycler_commenterEmail);
            feedbackComment = itemView.findViewById(R.id.feedback_comment);
            rating = itemView.findViewById(R.id.feedback_rating);
        }
    }
}
