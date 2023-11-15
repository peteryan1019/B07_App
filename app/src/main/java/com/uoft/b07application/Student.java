package com.uoft.b07application;

public class Student {
    private String name;
    private String email;
    // Add other properties as needed

    // Constructor, getters and setters
    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Student.class)
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
        // Initialize other properties
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Additional getters and setters for other properties
}

