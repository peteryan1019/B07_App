package com.uoft.b07application.ui.event;

import java.util.HashMap;

public class EventModel {
    String eventName;
    String eventDate;
    String key;

    public EventModel(HashMap<String, String> event) {
        this.eventName = event.get("eventName");
        this.eventDate = event.get("eventDate");
        this.key = event.get("key");
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
