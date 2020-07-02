package com.example.calculatorapp;

public class Note {

    String title, description, date;

    Note(String setTitle, String setDescription, String setDate) {
        title = setTitle;
        description = setDescription;
        date = setDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
