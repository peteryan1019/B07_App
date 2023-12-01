package com.uoft.b07application.ui.viewfeedback;

import java.util.HashMap;

public class FeedbackModel {
    String commenterEmail, commenterName, rating, feedbackComment;

    public FeedbackModel(HashMap<String, String> childHashMap) {
        commenterEmail = childHashMap.get("commenterEmail");
        commenterName = childHashMap.get("commenterName");
        feedbackComment = childHashMap.get("feedbackComment");
        rating = childHashMap.get("rating");
    }

    public String getCommenterEmail() {
        return commenterEmail;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public String getRating() {
        return rating;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }
}
