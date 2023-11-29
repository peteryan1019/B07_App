package com.uoft.b07application.ui.student;

public class Feedback {
    private String name;
    private String eventName;
    private float rating;
    private String feedbackComment;

    // Default constructor required for Firebase
    public Feedback() {}

    public Feedback(String name, String eventName, float rating, String feedbackComment) {
        this.name = name;
        this.eventName = eventName;
        this.rating = rating;
        this.feedbackComment = feedbackComment;
    }

    public String getName() {
        return name;
    }

    public String getEventName() {
        return eventName;
    }

    public float getRating() {
        return rating;
    }

    public String getFeedbackComment() {
        return feedbackComment;
    }
}
