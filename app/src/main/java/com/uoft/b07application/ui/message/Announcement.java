package com.uoft.b07application.ui.message;

public class Announcement {
    private String message;
    private String recipient;
    private String subject;
    public Announcement(String message, String recipient, String subject){
        this.message = message;
        this.recipient = recipient;
        this.subject = subject;
    }
}
