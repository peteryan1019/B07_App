package com.uoft.b07application.ui.admin;

public class AnnouncementModel {
    String senderUsername;
    String senderEmail;
    String recipient;
    String subject;
    String message;
    public AnnouncementModel(String senderUsername, String senderEmail
            , String recipient, String subject, String message){
        this.senderUsername = senderUsername;
        this.senderEmail = senderEmail;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
