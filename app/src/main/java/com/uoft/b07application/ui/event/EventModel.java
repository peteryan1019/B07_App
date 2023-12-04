package com.uoft.b07application.ui.event;

import java.util.HashMap;

public class EventModel {
    private String eventName;
    private String eventDate;
    private String key;

    public EventModel(HashMap<String, Object> event) {
        this.eventName = (String) event.get("eventName");
        this.eventDate = (String) event.get("eventDate");
        this.key = (String) event.get("key");
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getKey() {
        return key;
    }

}

